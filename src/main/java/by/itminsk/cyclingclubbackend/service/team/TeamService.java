package by.itminsk.cyclingclubbackend.service.team;

import by.itminsk.cyclingclubbackend.model.user.Team;

import java.util.List;

public interface TeamService {

    Team getTeam(String name);

    List<Team> getTeams();
}
