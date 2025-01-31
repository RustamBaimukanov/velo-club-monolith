package com.work.veloclub.controller;

import com.work.veloclub.mapper.team.TeamMapper;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamCreateRequest;
import com.work.veloclub.model.team.TeamUpdateRequest;
import com.work.veloclub.service.team.TeamService;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teams")
@RequiredArgsConstructor
@Tag(name = "Команды", description = "Операции связанные с командами")
public class TeamController {

    private final TeamService teamService;


    @Operation(
            summary = "Добавление команды",
            description = "API добавления команды.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь создал команду"),
                    @ApiResponse(responseCode = "400", description = "Запрос не прошел через валидацию.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping
    public ResponseEntity<?> addTeam(@RequestBody @Valid TeamCreateRequest teamDTO) {
        Team team = teamService.createTeam(teamDTO);
        return ResponseEntity.ok(TeamMapper.mapToTeamResponse(team));
    }

    @Operation(
            summary = "Добавление команд",
            description = "API добавления команд(генерация данных, удалить в следующих циклах)."
    )
    @PostMapping("/generate")
    public ResponseEntity<?> addTeamGenerate(@RequestBody List<TeamCreateRequest> teamsDTO) {
        List<Team> teams = teamService.createTeamsGenerate(teamsDTO);
        return ResponseEntity.ok(TeamMapper.mapToTeamResponse(teams));
    }

    @Operation(
            summary = "Редактирование команды",
            description = "API добавления команды.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь обновил команду"),
                    @ApiResponse(responseCode = "400", description = "Запрос не прошел через валидацию.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody @Valid TeamUpdateRequest teamDTO) {
        teamService.teamExistenceValidator(id);
        Team team = teamService.update(id, teamDTO);
        return ResponseEntity.ok(TeamMapper.mapToTeamResponse(team));
    }


    @Operation(
            summary = "Получение команды",
            description = "API получения команды",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил команду по id"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        teamService.teamExistenceValidator(id);
        return ResponseEntity.ok(TeamMapper.mapToTeamDto(teamService.getTeam(id)));
    }

    @Operation(
            summary = "Получение списка команд",
            description = "API получения команд сразу вместе со списком членов каждой команды или без них",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил список команд"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping
    public ResponseEntity<?> getTeams(@RequestParam(value = "withMembers", required = false) Boolean withMembers) {
        if (withMembers == null) {
            return ResponseEntity.ok(TeamMapper.mapToTeamDtoList(teamService.getTeam()));
        }
        return ResponseEntity.ok(TeamMapper.mapToTeamWithUsersDtoList(teamService.getTeamWithUsers()));
    }

    @Operation(
            summary = "Получение членов команды",
            description = "API получения членов команды",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил список членов команды"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping("/{id}/users")
    public ResponseEntity<?> getTeamUsers(@PathVariable Long id) {
        teamService.teamExistenceValidator(id);
        return ResponseEntity.ok(teamService.getTeamUsers(id));
    }
}
