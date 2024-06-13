package by.itminsk.cyclingclubbackend.social_network;

import by.itminsk.cyclingclubbackend.user.User;

import java.util.Map;

public interface SocialNetworkService {

    void saveSocialNetworksWhenUserEdit(User user, Map<SocialNetworkEnum, String> socialNetworks);
}
