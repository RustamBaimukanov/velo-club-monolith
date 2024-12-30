package com.work.veloclub.controller;

import com.work.veloclub.mapper.event.EventMapper;
import com.work.veloclub.mapper.team.TeamMapper;
import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventCreateDTO;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamCreateRequest;
import com.work.veloclub.model.team.TeamDTO;
import com.work.veloclub.model.team.TeamUpdateRequest;
import com.work.veloclub.service.team.TeamService;
import io.swagger.v3.oas.annotations.Operation;
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
            description = "API добавления команды(будет дополнено, в следующем цикле)."
    )
    @PostMapping
    public ResponseEntity<?> addTeam(@RequestBody @Valid TeamCreateRequest teamDTO) {
        //eventService.validateCreateEventContent(eventDto);
        Team team = teamService.createTeam(teamDTO);
        return ResponseEntity.ok(TeamMapper.mapToTeamResponse(team));
    }

    @Operation(
            summary = "Добавление команд",
            description = "API добавления команд(генерация данных, удалить в следующих циклах)."
    )
    @PostMapping("/generate")
    public ResponseEntity<?> addTeamGenerate(@RequestBody List<TeamCreateRequest> teamsDTO) {
        //eventService.validateCreateEventContent(eventDto);
        List<Team> teams = teamService.createTeamsGenerate(teamsDTO);
        return ResponseEntity.ok(TeamMapper.mapToTeamResponse(teams));
    }

    @Operation(
            summary = "Добавление команды",
            description = "API добавления команды(будет дополнено, в следующем цикле)."
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody @Valid TeamUpdateRequest teamDTO) {
        //eventService.validateCreateEventContent(eventDto);
        Team team = teamService.update(id, teamDTO);
        return ResponseEntity.ok(TeamMapper.mapToTeamResponse(team));
    }


    @Operation(
            summary = "Получение команды/команд",
            description = "API получения команд/команды"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        return ResponseEntity.ok(TeamMapper.mapToTeamDto(teamService.getTeam(id)));
    }

    @GetMapping
    public List<TeamDTO> getTeams(){
        return TeamMapper.mapToTeamDtoList(teamService.getTeam());
    }

    @Operation(
            summary = "Получение членов команды",
            description = "API получения членов команды"
    )
    @GetMapping("/{id}/users")
    public ResponseEntity<?> getTeamUsers(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamUsers(id));
    }
}
