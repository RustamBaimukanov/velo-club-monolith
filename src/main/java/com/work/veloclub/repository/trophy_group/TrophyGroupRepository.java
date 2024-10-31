package com.work.veloclub.repository.trophy_group;

import com.work.veloclub.model.trophy_group.TrophyGroup;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface TrophyGroupRepository extends JpaRepository<TrophyGroup, Long> {

}
