package com.work.veloclub.model.team;

import com.work.veloclub.model.BaseEntity;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    private byte[] photo;
    private String photoFormat;


    @OneToMany(mappedBy = "team")
    private Set<UserProfile> userProfiles = new HashSet<>();

}
