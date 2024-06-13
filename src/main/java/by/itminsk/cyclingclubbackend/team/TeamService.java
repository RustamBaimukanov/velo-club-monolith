package by.itminsk.cyclingclubbackend.team;

import by.itminsk.cyclingclubbackend.team.Team;
import by.itminsk.cyclingclubbackend.team.dto.TeamDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserGetDto;

import java.util.List;

public interface TeamService {
    TeamDTO getTeam(Long id);
    List<TeamDTO> getTeam();

    List<UserGetDto> getTeamUsers(Long id);
}
