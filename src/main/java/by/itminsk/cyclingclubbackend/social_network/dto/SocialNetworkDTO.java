package by.itminsk.cyclingclubbackend.social_network.dto;

import by.itminsk.cyclingclubbackend.social_network.SocialNetworkEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SocialNetworkDTO {

    private Long id;

    private String account;

    private SocialNetworkEnum name;

    public SocialNetworkDTO(Long id, SocialNetworkEnum name, String account) {
        this.id = id;
        this.account = account;
        this.name = name;
    }
}
