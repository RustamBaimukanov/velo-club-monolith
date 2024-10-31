package com.work.veloclub.model.social_network;

import com.work.veloclub.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social_networks",
        uniqueConstraints = { @UniqueConstraint( name = "UniqueUserAndName", columnNames = { "user_id", "name" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialNetwork {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private SocialNetworkEnum name;

    @Column(name = "account")
    private String account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
