package by.itminsk.cyclingclubbackend.service.social_network;

import by.itminsk.cyclingclubbackend.model.social_network.SocialNetwork;
import by.itminsk.cyclingclubbackend.model.social_network.SocialNetworkEnum;
import by.itminsk.cyclingclubbackend.repository.social_network.SocialNetworkRepository;
import by.itminsk.cyclingclubbackend.model.social_network.SocialNetworkDTO;
import by.itminsk.cyclingclubbackend.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SocialNetworkServiceImpl implements SocialNetworkService{

    private final SocialNetworkRepository socialNetworkRepository;
    @Override
    public void saveSocialNetworksWhenUserEdit(User user, Map<SocialNetworkEnum, String> socialNetworks) {
        Set<SocialNetwork> socialNetworksToSave = new HashSet<>();
        if (socialNetworkRepository.existsByUser_Id(user.getId())){
            Set<SocialNetworkDTO> existSocialNetworks = socialNetworkRepository.findAllByUserId(user.getId());
            for (SocialNetworkDTO socialNetwork:
                 existSocialNetworks) {
                socialNetworksToSave.add(SocialNetwork.builder()
                        .id(socialNetwork.getId())
                        .user(user)
                        .name(socialNetwork.getName()).account(socialNetworks.get(socialNetwork.getName())).build());
            }
        }
        else {
            for (Map.Entry<SocialNetworkEnum,String> socialNetwork:
                    socialNetworks.entrySet()) {
                socialNetworksToSave.add(SocialNetwork.builder().user(user).name(socialNetwork.getKey()).account(socialNetwork.getValue()).build());
            }
        }

        socialNetworkRepository.saveAll(socialNetworksToSave);
    }
}