package com.work.veloclub.repository.team;

import com.work.veloclub.model.team.Team;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByName(String name);

    @Query(value =
            """
                            select t from Team t
                             left join fetch t.userProfiles profiles
                    """)
    List<Team> findAllWithUsers();

}
