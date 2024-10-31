package com.work.veloclub.model.race;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "race")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Race {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "route_start_point")
    private String routeStartPoint;

    @Column(name = "route_end_point")
    private String routeEndPoint;

    //В метрах
    @Column(name = "distance")
    private Double distance;

    //В миллисекундах
    @Column(name = "race_time")
    private Double raceTime;

    @Column(name = "calories")
    private Double calories;

    //TODO количество оценок от пользователей
}
