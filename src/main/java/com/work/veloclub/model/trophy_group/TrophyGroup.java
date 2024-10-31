package com.work.veloclub.model.trophy_group;

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
