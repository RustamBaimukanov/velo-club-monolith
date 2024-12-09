package com.work.veloclub.model.social_network;

import com.work.veloclub.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social_networks",
        uniqueConstraints = { @UniqueConstraint( name = "UniqueUserAndName", columnNames = { "user_profile_id", "name" }) })
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialNetwork that = (SocialNetwork) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
