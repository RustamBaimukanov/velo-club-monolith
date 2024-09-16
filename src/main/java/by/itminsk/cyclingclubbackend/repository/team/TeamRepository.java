package by.itminsk.cyclingclubbackend.repository.team;

import by.itminsk.cyclingclubbackend.model.team.Team;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByName(String name);

}
