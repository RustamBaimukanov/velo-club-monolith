package by.itminsk.cyclingclubbackend.model.trophy_type;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trophy_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrophyType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

//    @Column(name = "svg", length = 4000)
//    private String svg;


    
}
