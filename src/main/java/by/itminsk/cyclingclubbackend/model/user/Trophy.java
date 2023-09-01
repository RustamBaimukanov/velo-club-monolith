package by.itminsk.cyclingclubbackend.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trophies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Trophy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "type_id")
    private TrophyType trophyType;

}
