package com.work.veloclub.model.feedback;

import com.work.veloclub.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "message")
    private String message;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
}
