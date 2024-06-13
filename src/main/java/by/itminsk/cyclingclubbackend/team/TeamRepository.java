package by.itminsk.cyclingclubbackend.team;

import by.itminsk.cyclingclubbackend.team.Team;
import by.itminsk.cyclingclubbackend.team.dto.TeamDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByName(String name);

}
