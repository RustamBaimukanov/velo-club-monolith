package by.itminsk.cyclingclubbackend.service.team;

import by.itminsk.cyclingclubbackend.model.team.TeamDTO;
import by.itminsk.cyclingclubbackend.model.user.UserGetDto;

import java.util.List;

public interface TeamService {
    TeamDTO getTeam(Long id);
    List<TeamDTO> getTeam();

    List<UserGetDto> getTeamUsers(Long id);

    void teamExistenceValidator(Long id);

}
