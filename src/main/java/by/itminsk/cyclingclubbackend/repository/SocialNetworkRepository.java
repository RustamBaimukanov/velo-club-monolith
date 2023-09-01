package by.itminsk.cyclingclubbackend.repository;

import by.itminsk.cyclingclubbackend.model.user.SocialNetwork;
import by.itminsk.cyclingclubbackend.model.user.SocialNetworkDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Integer> {

    Set<SocialNetworkDTO> findAllByUserId(Long userId);
}
