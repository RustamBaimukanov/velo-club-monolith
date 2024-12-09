package com.work.veloclub.model.news;

import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event_profile.EventUserProfile;
import com.work.veloclub.model.news_metainfo.NewsMetaInfo;
import com.work.veloclub.model.news_profile.NewsUserProfile;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.work.veloclub.model.user_profile.UserProfile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 4000)
    private String content;

    @Column(name = "creation_date")
    private LocalDate createdAt;

    @OneToMany
    @JoinColumn(name = "news_id")
    private Set<NewsMetaInfo> metaInfo = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> availableRoles = new HashSet<>();

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NewsUserProfile> additionalUsers = new HashSet<>();

    public News addProfile(UserProfile profile) {
        NewsUserProfile newsUserProfile = new NewsUserProfile(this, profile);
        additionalUsers.add(newsUserProfile);
        profile.getNews().add(newsUserProfile);
        return this;
    }

    public void removeProfile(UserProfile profile) {
        NewsUserProfile newsUserProfile = new NewsUserProfile(this, profile);
        profile.getNews().remove(newsUserProfile);
        additionalUsers.remove(newsUserProfile);
        newsUserProfile.setNews(null);
        newsUserProfile.setUserProfile(null);
    }
}
