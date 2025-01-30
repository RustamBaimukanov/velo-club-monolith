package com.work.veloclub.service.team;

import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamCreateRequest;
import com.work.veloclub.model.team.TeamDTODeprecated;
import com.work.veloclub.model.team.TeamUpdateRequest;
import com.work.veloclub.model.user.UserGetDto;

import java.util.List;

public interface TeamService {
    Team getTeam(Long id);
    List<Team> getTeam();

    List<UserGetDto> getTeamUsers(Long id);

    List<Team> getTeamWithUsers();

    /**
     * Проверяет существование команды по введенному пользователем id
     * Если проверка не проходит, инициирует ответ с кодом 400
     * @param id - id команды
     */
    void teamExistenceValidator(Long id);

    Team createTeam(TeamCreateRequest teamDTO);

    Team update(Long id, TeamUpdateRequest teamDTO);

    List<Team> createTeamsGenerate(List<TeamCreateRequest> teamsDTO);
}
