package by.itminsk.cyclingclubbackend.team;

import by.itminsk.cyclingclubbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany
//    @JoinColumn(name = "team_id")
//    private Set<User> users = new HashSet<>();

}
