package com.work.veloclub.mapper.social_network;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.city.CityDTO;
import com.work.veloclub.model.social_network.SocialNetwork;
import com.work.veloclub.model.social_network.SocialNetworkDTO;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SocialNetworkMapper {

    public static SocialNetworkDTO mapToSocialNetworkDto(SocialNetwork socialNetwork){
        if (socialNetwork == null){
            return null;
        }

        return new SocialNetworkDTO(socialNetwork.getId(), socialNetwork.getAccount(), socialNetwork.getName());
    }

    public static List<SocialNetworkDTO> mapToSocialNetworkDtoList(Set<SocialNetwork> socialNetworks){
        if (socialNetworks == null || socialNetworks.isEmpty()) {
            return Collections.emptyList();  // Возвращаем пустой список, если исходный список null или пустой
        }

        return socialNetworks.stream()
                .map(SocialNetworkMapper::mapToSocialNetworkDto)
                .collect(Collectors.toList());
    }
}
