package by.itminsk.cyclingclubbackend.model.trophy;

import by.itminsk.cyclingclubbackend.model.trophy_group.TrophyGroup;
import by.itminsk.cyclingclubbackend.model.trophy_type.TrophyType;
import by.itminsk.cyclingclubbackend.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trophies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trophy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TrophyType trophyType;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private TrophyGroup trophyGroup;

}