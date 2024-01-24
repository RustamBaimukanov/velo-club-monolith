package by.itminsk.cyclingclubbackend.social_network;

import by.itminsk.cyclingclubbackend.social_network.dto.SocialNetworkDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Integer> {

    Set<SocialNetworkDTO> findAllByUserId(Long userId);
}
