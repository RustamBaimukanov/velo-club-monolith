package by.itminsk.cyclingclubbackend.social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SocialNetworkDTO {

    private String account;

    private String name;

    public SocialNetworkDTO(String name, String account) {
        this.account = account;
        this.name = name;
    }
}
