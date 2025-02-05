package com.work.veloclub.model.event_result;

import com.work.veloclub.model.BaseEntity;
import com.work.veloclub.model.event.Event;

import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "event_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResult extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "place")
    private Integer place;


    @Column(name = "points")
    private Integer points;

    @Column(name = "race_time")
    private Double raceTime;

    @Column(name = "start_order")
    private Integer startOrder;

    @Column(name = "start_time")
    private LocalDateTime startTime;



    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ParticipantsStatus name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

}
