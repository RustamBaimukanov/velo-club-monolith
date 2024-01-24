package by.itminsk.cyclingclubbackend.team;

import by.itminsk.cyclingclubbackend.team.Team;

import java.util.List;

public interface TeamService {

    Team getTeam(String name);

    Team getTeamById(Long id);


    List<Team> getTeams();
}
