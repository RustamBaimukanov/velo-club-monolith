package by.itminsk.cyclingclubbackend.news;

import by.itminsk.cyclingclubbackend.news_metainfo.NewsMetaInfo;
import by.itminsk.cyclingclubbackend.role.Role;
import by.itminsk.cyclingclubbackend.social_network.SocialNetwork;
import by.itminsk.cyclingclubbackend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    private Date creationDate;

    @OneToMany
    @JoinColumn(name = "news_id")
    private Set<NewsMetaInfo> socialNetworks = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "news_roles",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> availableRoles = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "news_users",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> availableUsers = new HashSet<>();
}
