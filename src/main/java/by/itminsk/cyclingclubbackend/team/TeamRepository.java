package by.itminsk.cyclingclubbackend.team;

import by.itminsk.cyclingclubbackend.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamById(Long id);

    Team findTeamByName(String name);

    List<Team> findAll();

}
