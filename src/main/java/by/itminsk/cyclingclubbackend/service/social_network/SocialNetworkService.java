package by.itminsk.cyclingclubbackend.service.social_network;

import by.itminsk.cyclingclubbackend.model.social_network.SocialNetworkEnum;
import by.itminsk.cyclingclubbackend.model.user.User;

import java.util.Map;

public interface SocialNetworkService {

    void saveSocialNetworksWhenUserEdit(User user, Map<SocialNetworkEnum, String> socialNetworks);
}
