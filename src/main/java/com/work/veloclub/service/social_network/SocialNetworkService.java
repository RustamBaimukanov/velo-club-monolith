package com.work.veloclub.service.social_network;

import com.work.veloclub.model.social_network.SocialNetworkEnum;
import com.work.veloclub.model.user.User;

import java.util.Map;

public interface SocialNetworkService {

    void saveSocialNetworksWhenUserEdit(User user, Map<SocialNetworkEnum, String> socialNetworks);
}
