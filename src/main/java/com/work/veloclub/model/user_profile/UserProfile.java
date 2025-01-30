package com.work.veloclub.model.user_profile;

import com.work.veloclub.model.answer.Answer;
import com.work.veloclub.model.city.City;
//import com.work.veloclub.model.event_result.EventResult;
//import com.work.veloclub.model.news.News;
import com.work.veloclub.model.event_profile.EventUserProfile;
import com.work.veloclub.model.news_profile.NewsUserProfile;
import com.work.veloclub.model.social_network.SocialNetwork;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.trophy.Trophy;
import com.work.veloclub.model.user.GenderEnum;
import com.work.veloclub.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String email;

    private String firstName;

    private String lastName;

    private String surname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private Double height;
    private Double weight;
    private byte[] photo;
    private String photoFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private Set<SocialNetwork> socialNetworks = new HashSet<>();

//    @OneToMany(mappedBy = "userProfile")
//    private Set<EventResult> eventResults = new HashSet<>();

    @OneToMany(mappedBy = "userProfile")
    private Set<Trophy> trophies = new HashSet<>();

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventUserProfile> events = new HashSet<>();

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NewsUserProfile> news = new HashSet<>();
//
//    @ManyToMany(mappedBy = "availableUsers")
//    private Set<News> news;

    @ManyToMany(mappedBy = "users")
    private Set<Answer> answers = new HashSet<>();


    public UserProfile addAnswer(Answer answer) {
        answers.add(answer);
        answer.getUsers().add(this);
        return this;
    }

    public UserProfile removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.getUsers().remove(this);
        return this;
    }

    public void addSocialNetwork(SocialNetwork network){
        socialNetworks.add(network);
        network.setUserProfile(this);
    }

    public void removeSocialNetwork(SocialNetwork network){
        socialNetworks.remove(network);
        network.setUserProfile(null);
    }


}
