package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.r_city.City;
import by.itminsk.cyclingclubbackend.race.Race;
import by.itminsk.cyclingclubbackend.role.Role;
import by.itminsk.cyclingclubbackend.user.User;
import by.itminsk.cyclingclubbackend.user.dto.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "date")
    private Date date;

    @Column(name = "available_birth_date_from")
    private Date availableBirthDateFrom;

    @Column(name = "available_birth_date_to")
    private Date availableBirthDateTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "available_gender")
    private GenderEnum availableGender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "events_roles",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> availableRoles = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> availableUsers = new HashSet<>();
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
}
