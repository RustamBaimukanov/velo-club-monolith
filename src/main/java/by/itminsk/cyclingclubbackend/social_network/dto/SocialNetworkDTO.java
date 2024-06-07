package by.itminsk.cyclingclubbackend.social_network.dto;

import by.itminsk.cyclingclubbackend.social_network.SocialNetworkEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SocialNetworkDTO {

    private String account;

    private SocialNetworkEnum name;

    public SocialNetworkDTO(SocialNetworkEnum name, String account) {
        this.account = account;
        this.name = name;
    }
}
