package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.EventService;
import by.itminsk.cyclingclubbackend.team.TeamService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Tag(name = "Команды", description = "Операции связанные с командами(на данный момент тут нету добавления/редактирования команды, отвлекают собаки...)")
public class TeamController {

    private final TeamService teamService;

    @Operation(
            summary = "Получение команды/команд",
            description = "API получения команд/команды"
    )
    @GetMapping("/team")
    public ResponseEntity<?> getTeam(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(teamService.getTeam(id));
        } else {
            return ResponseEntity.ok(teamService.getTeam());
        }
    }

    @Operation(
            summary = "Получение членов команды",
            description = "API получения членов команды"
    )
    @GetMapping("/team/{id}/users")
    public ResponseEntity<?> getTeamUsers(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamUsers(id));
    }
}
