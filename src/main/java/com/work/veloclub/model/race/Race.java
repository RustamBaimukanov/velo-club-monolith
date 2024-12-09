package com.work.veloclub.model.race;

import com.work.veloclub.model.BaseEntity;
import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "race")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Race extends BaseEntity {

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
    @Column(name = "distance", scale = 2)
    private Double distance;

    @Column(name = "average_speed", scale = 2)
    private Double averageSpeed;

    //В миллисекундах
    @Column(name = "race_time")
    private Double raceTime;

    @Column(name = "calories")
    private Double calories;

    @OneToMany(mappedBy = "race")
    private Set<Event> events = new HashSet<>();

    //TODO количество оценок от пользователей
}
