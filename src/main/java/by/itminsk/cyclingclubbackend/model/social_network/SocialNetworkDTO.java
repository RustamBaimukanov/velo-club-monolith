package by.itminsk.cyclingclubbackend.model.social_network;

import by.itminsk.cyclingclubbackend.model.social_network.SocialNetworkEnum;
import lombok.Getter;
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
