package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.EventService;
import by.itminsk.cyclingclubbackend.team.TeamService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/team")
    public ResponseEntity<?> getTeam(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(teamService.getTeam(id));
        } else {
            return ResponseEntity.ok(teamService.getTeam());
        }
    }

    @GetMapping("/team/{id}/users")
    public ResponseEntity<?> getTeamUsers(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamUsers(id));
    }
}
