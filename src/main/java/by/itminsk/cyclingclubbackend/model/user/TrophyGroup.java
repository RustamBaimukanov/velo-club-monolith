package by.itminsk.cyclingclubbackend.model.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trophy_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrophyGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

}
