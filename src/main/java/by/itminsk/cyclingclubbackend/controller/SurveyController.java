package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.mapper.survey.SurveyMapper;
import by.itminsk.cyclingclubbackend.model.survey.dto.CreateSurveyRequest;
import by.itminsk.cyclingclubbackend.model.survey.dto.SurveyResponse;
import by.itminsk.cyclingclubbackend.model.survey.dto.UpdateSurveyRequest;
import by.itminsk.cyclingclubbackend.service.survey.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/survey")
@RequiredArgsConstructor
@Tag(name = "Опросы")
public class SurveyController {

    private final SurveyService surveyService;

    private final SurveyMapper surveyMapper;

    @Operation(
            summary = "Добавление опроса")
    @PostMapping
    public ResponseEntity<?> addSurvey(@RequestBody CreateSurveyRequest createSurveyRequest) {
        surveyService.createSurvey(surveyMapper.createSurveyRequestToSurvey(createSurveyRequest));
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Редактирование опроса")
    @PutMapping
    public ResponseEntity<?> updateSurvey(
            @RequestBody UpdateSurveyRequest updateSurveyRequest) {
        surveyService.updateSurvey(surveyMapper.updateSurveyRequestToSurvey(updateSurveyRequest));
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Удаление опроса")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSurvey(
            @PathVariable Long id) {
        surveyService.removeSurvey(id);
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "Получить 1 опрос")
    @GetMapping("{id}")
    public ResponseEntity<SurveyResponse> getSurvey(
            @PathVariable Long id
    ) {
        SurveyResponse surveyResponse = surveyMapper.surveyToSurveyResponse(surveyService.getSurvey(id));
        return ResponseEntity.ok(surveyResponse);
    }

    @Operation(
            summary = "Получить все опросы")
    @GetMapping
    public ResponseEntity<List<SurveyResponse>> getSurvey() {
        List<SurveyResponse> surveyResponses = surveyMapper.surveyToSurveyResponse(surveyService.getSurvey());
        return ResponseEntity.ok(surveyResponses);
    }
}
