package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import by.itminsk.cyclingclubbackend.race.RaceMapper;
import by.itminsk.cyclingclubbackend.race.RaceService;
import by.itminsk.cyclingclubbackend.race.dto.RaceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Tag(name = "Маршруты", description = "Операции связанные с маршрутами")
public class RaceController {

    private final RaceService raceService;

    private final RaceMapper raceMapper;

    @Operation(
            summary = "Добавление маршрута",
            description = "API добавления маршрута(на данный момент скудный набор данных,дополнительные поля будут добавлены после обсуждения)"
    )
    @PostMapping(value = "/race", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addRace(@Valid @ModelAttribute RaceDto race) {
        raceService.createRace(raceMapper.fromRaceDto(race));
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Редактирование существующего маршрута",
            description = "API редактирования существующего маршрута(на данный момент скудный набор данных,дополнительные поля будут добавлены после обсуждения, к тому же нужно будет предусмотреть следующий случай-что делать с редактирование маршрута если есть активное событие привязанное к этому маршруту)"
    )
    @PutMapping(value = "/race", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateRace(@Valid @ModelAttribute RaceDto race) {
        raceService.updateRace(raceMapper.fromRaceDto(race));
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Получение маршрута/маршрутов",
            description = "API получения маршрута/маршрутов, дополнительный параметр id определяет, получать обычный список маршрутов или только лучшие, отсюда следует-какие критерии определяют преимущества 'лучших' маршрутов перед другими? Количество использовании в событиях, рейтинги, оценки?"
    )
    @GetMapping(value = "/race")
    public ResponseEntity<?> getRace(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false, defaultValue = "false") Boolean isRelevant) {
        if (id != null) {
            return ResponseEntity.ok(raceMapper.toRaceDto(raceService.getRace(id)));
        } else {
            return ResponseEntity.ok(raceMapper.toRaceDtoList(raceService.getRace(isRelevant)));
        }
    }
}
