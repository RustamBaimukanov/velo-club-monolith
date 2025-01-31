package com.work.veloclub.service.team;

import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamCreateRequest;
import com.work.veloclub.model.team.TeamDTODeprecated;
import com.work.veloclub.model.team.TeamUpdateRequest;
import com.work.veloclub.model.user.UserGetDto;

import java.util.List;

public interface TeamService {

    /**
     * Отдает команду по id
     * Если запрашиваемой команды нет, инициирует ответ с кодом 400
     * @param id - id команды
     * @return возвращает команду
     */
    Team getTeam(Long id);

    /**
     * Отдает команды списком
     * @return возвращает список команд
     */
    List<Team> getTeam();

    /**
     * Отдает список членов команды
     * Если команды по id нет, инициирует ответ с кодом 400
     * @param id - id команды
     * @return возвращает список мемберов
     */
    List<UserGetDto> getTeamUsers(Long id);

    /**
     * Отдает команды списком вместе с мемберами каждой команды
     * @return возвращает список команд с мемберами
     */
    List<Team> getTeamWithUsers();

    /**
     * Проверяет существование команды по введенному пользователем id
     * Если проверка не проходит, инициирует ответ с кодом 400
     * @param id - id команды
     */
    void teamExistenceValidator(Long id);

    /**
     * Создает команду и отдает результат обратно
     * @param teamDTO данные для создания команды
     * @return возвращает созданную команду
     */
    Team createTeam(TeamCreateRequest teamDTO);

    /**
     * Редактирует команду и отдает результат обратно
     * Если редактируемой команды по какой-то причине не существует, инициирует ответ с кодом 400
     * @param teamDTO данные для редактирования команды
     * @return возвращает отредактированную команду
     */
    Team update(Long id, TeamUpdateRequest teamDTO);

    List<Team> createTeamsGenerate(List<TeamCreateRequest> teamsDTO);
}
