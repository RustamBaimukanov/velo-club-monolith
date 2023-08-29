package by.itminsk.cyclingclubbackend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "social_networks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialNetwork {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "account")
    private String account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public SocialNetwork(String name, String link) {
        this.name = name;
        this.account = link;
    }

    public SocialNetwork(SocialNetworkDTO socialNetworkDTO) {
        this.name = socialNetworkDTO.getName();
        this.account = socialNetworkDTO.getLink();
        this.user = socialNetworkDTO.getUser();
    }
}
