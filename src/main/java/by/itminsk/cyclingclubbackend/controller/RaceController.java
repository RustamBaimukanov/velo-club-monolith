package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import by.itminsk.cyclingclubbackend.race.RaceService;
import by.itminsk.cyclingclubbackend.race.dto.RaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @PostMapping(value = "/race", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addRace(@ModelAttribute RaceDto race) {
        raceService.createRace(race);
        return ResponseEntity.ok("");
    }

    @PutMapping(value = "/race", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateRace(@ModelAttribute RaceDto race) {
        raceService.updateRace(race);
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/race")
    public ResponseEntity<?> getRace(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false, defaultValue = "false") Boolean isRelevant) {
        if (id != null) {
            return ResponseEntity.ok(raceService.getRace(id));
        } else {
            return ResponseEntity.ok(raceService.getRace(isRelevant));
        }

    }
}
