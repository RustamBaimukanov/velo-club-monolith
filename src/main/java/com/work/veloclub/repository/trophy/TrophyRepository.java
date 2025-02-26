package com.work.veloclub.repository.trophy;

import com.work.veloclub.model.trophy.Trophy;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Hidden
public interface TrophyRepository extends JpaRepository<Trophy, Long> {

//    Set<Trophy> findAllByUserId(Long userId);

}
