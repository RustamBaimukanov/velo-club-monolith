package com.work.veloclub.repository.social_network;

import com.work.veloclub.model.social_network.SocialNetwork;
import com.work.veloclub.model.social_network.SocialNetworkDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Hidden
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Integer> {

    Set<SocialNetworkDTO> findAllByUserId(Long userId);

    Boolean existsByUser_Id(Long userId);

}
