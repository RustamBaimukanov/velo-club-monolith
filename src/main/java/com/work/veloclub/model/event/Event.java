package com.work.veloclub.model.event;

import com.work.veloclub.model.BaseEntity;
import com.work.veloclub.model.city.City;
import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.event_profile.EventUserProfile;
import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.race.Race;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "birth_date_from")
    private LocalDate birthDateFrom;

    @Column(name = "birth_date_to")
    private LocalDate birthDateTo;

    //Время проведения мероприятия от
    @Column(name = "start_date")
    private LocalDateTime startDate;

    //Время проведения мероприятия до
    @Column(name = "end_date")
    private LocalDateTime endDate;

    //Нужно добавить напоминание о мероприятии(веб сокеты?)


    @Enumerated(EnumType.STRING)
    @Column(name = "available_gender")
    private GenderEnum availableGender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_profile_id")
    private UserProfile createdUser;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> availableRoles = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventUserProfile> additionalUsers = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventResult> eventResults = new HashSet<>();

    @ManyToOne
    private Category category;

    // Метод для вычисления возраста
    private int calculateAge(LocalDate birthDate) {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // Фильтрация по возрасту
    public static List<Event> filterByAgeRange(List<Event> events, Integer ageFrom, Integer ageTo) {
        return events.stream()
                .filter(event -> {
                    int ageFromValid = ageFrom == null ? Integer.MIN_VALUE : ageFrom;
                    int ageToValid = ageTo == null ? Integer.MAX_VALUE : ageTo;

                    // Проверка возраста, если обе границы возрастных диапазонов заданы
                    int ageFromCalculated = event.calculateAge(event.getBirthDateFrom());
                    int ageToCalculated = event.calculateAge(event.getBirthDateTo());

                    return ageFromCalculated >= ageFromValid && ageToCalculated <= ageToValid;
                })
                .collect(Collectors.toList());
    }


//    @ManyToMany
//    @JoinTable(
//            name = "events_users",
//            joinColumns = @JoinColumn(name = "event_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_profile_id")
//    )
//    private Set<UserProfile> additionalUsers = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Event event)) return false;

        return new EqualsBuilder().append(getId(), event.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }

    public Event addProfile(UserProfile profile) {
        EventUserProfile eventUserProfile = new EventUserProfile(this, profile);
        additionalUsers.add(eventUserProfile);
        profile.getEvents().add(eventUserProfile);
        return this;
    }

    public void removeProfile(UserProfile profile) {
        EventUserProfile eventUserProfile = new EventUserProfile(this, profile);
        profile.getEvents().remove(eventUserProfile);
        additionalUsers.remove(eventUserProfile);
        eventUserProfile.setEvent(null);
        eventUserProfile.setUserProfile(null);
    }

}
