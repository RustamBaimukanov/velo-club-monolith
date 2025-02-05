package com.work.veloclub.controller;

import com.work.veloclub.mapper.race.RaceMapper;
import com.work.veloclub.model.race.RaceCreateRequest;
import com.work.veloclub.model.race.RaceUpdateRequest;
import com.work.veloclub.service.race.RaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/race")
@RequiredArgsConstructor
@Tag(name = "Маршруты", description = "Операции связанные с маршрутами")
public class RaceController {

    private final RaceService raceService;


    @Operation(
            summary = "Добавление маршрута",
            description = "API добавления маршрута",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь добавил маршрут"),
                    @ApiResponse(responseCode = "400", description = "Маршрут не прошел валидацию"),
            }
    )
    @PostMapping
    public ResponseEntity<?> addRace(@Valid @RequestBody RaceCreateRequest race) {
        //TODO исправить
        return ResponseEntity.ok(RaceMapper.mapToRaceResponse(raceService.createRace(race)));
    }

    @Operation(
            summary = "Добавление маршрутов(Генерация данных, будет удалено перед релизом)",
            description = "API добавления маршрута"
    )
    @PostMapping("/generate")
    public ResponseEntity<?> addRaceMany(@RequestBody List<RaceCreateRequest> race) {
        //TODO исправить
        return ResponseEntity.ok(RaceMapper.mapToRaceResponse(raceService.createRaceGenerate(race)));
    }

    @Operation(
            summary = "Редактирование существующего маршрута",
            description = "API редактирования существующего маршрута",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь обновил маршрут"),
                    @ApiResponse(responseCode = "400", description = "Маршрут не прошел валидацию"),
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRace(
            @PathVariable Long id,
            @Valid @RequestBody RaceUpdateRequest race) {
        //TODO исправить
        return ResponseEntity.ok(RaceMapper.mapToRaceResponse(raceService.updateRace(id, race)));
    }

    @Operation(
            summary = "Получение маршрутов",
            description = "",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил постраничный список маршрутов")
            }
    )
    @GetMapping("/pages")
    public ResponseEntity<?> getRace(
            @RequestParam(defaultValue = "0", value = "page") int page,
            @RequestParam(defaultValue = "10", value = "size") int size) {

        return ResponseEntity.ok(RaceMapper.mapToRaceResponse(raceService.getRace(page, size)));
    }

    @Operation(
            summary = "Получение маршрутов",
            description = "",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь получил постраничный список маршрутов")
            }
    )
    @GetMapping("/infinity")
    public ResponseEntity<?> getRaceInfinity() {

        return ResponseEntity.ok(RaceMapper.mapToRaceResponse(raceService.getRace()));
    }
}
