package com.work.veloclub.service.team;

import com.work.veloclub.model.team.TeamDTO;
import com.work.veloclub.model.user.UserGetDto;

import java.util.List;

public interface TeamService {
    TeamDTO getTeam(Long id);
    List<TeamDTO> getTeam();

    List<UserGetDto> getTeamUsers(Long id);

    void teamExistenceValidator(Long id);

}
