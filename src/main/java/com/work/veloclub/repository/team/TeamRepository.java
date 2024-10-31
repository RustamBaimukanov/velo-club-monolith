package com.work.veloclub.repository.team;

import com.work.veloclub.model.team.Team;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByName(String name);

}
