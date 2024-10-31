package by.itminsk.cyclingclubbackend.model.user;

import by.itminsk.cyclingclubbackend.model.answer.Answer;
import by.itminsk.cyclingclubbackend.model.city.City;
import by.itminsk.cyclingclubbackend.model.event_result.EventResult;
import by.itminsk.cyclingclubbackend.model.news.News;
import by.itminsk.cyclingclubbackend.model.role.Role;
import by.itminsk.cyclingclubbackend.model.social_network.SocialNetwork;
import by.itminsk.cyclingclubbackend.model.team.Team;
import by.itminsk.cyclingclubbackend.model.trophy.Trophy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable, UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    @Email
    @Size(max = 255, message = "Длина почты слишком велика.")
    private String email;

    @Column(name = "phone_number", length = 13)
    @Pattern(regexp="^[+\\d](?:.*\\d)?$", message = "Формат номера телефона не верен.")
    @Size(min = 13, max = 13, message = "Некорректная длина номера телефона.")
    private String phoneNumber;

    @Column(name = "first_name")
    @Size(max = 255, message = "Длина имени слишком велика.")
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 255, message = "Длина фамилии слишком велика.")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private GenderEnum sex;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "photo", columnDefinition = "bytea")
    private byte[] photo;

    @Column(name = "photo-format")
    private String photoFormat;

    @Transient
    private String confirmPassword;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<SocialNetwork> socialNetworks = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<EventResult> eventResults = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Trophy> trophies = new HashSet<>();

    @ManyToMany(mappedBy = "availableUsers")
    Set<News> news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(mappedBy = "users")
    Set<Answer> answers = new HashSet<>();

    public User addAnswer(Answer answer) {
        answers.add(answer);
        answer.getUsers().add(this);
        return this;
    }

    public User removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.getUsers().remove(this);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role.getName().name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
