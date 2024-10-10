package by.itminsk.cyclingclubbackend.controller.survey;

import by.itminsk.cyclingclubbackend.mapper.survey.SurveyMapper;
import by.itminsk.cyclingclubbackend.model.question.Question;
import by.itminsk.cyclingclubbackend.model.survey.Survey;
import by.itminsk.cyclingclubbackend.model.survey.dto.CreateSurveyRequest;
import by.itminsk.cyclingclubbackend.model.survey.dto.SurveyResponse;
import by.itminsk.cyclingclubbackend.model.survey.dto.UpdateSurveyRequest;
import by.itminsk.cyclingclubbackend.service.survey.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/survey")
@RequiredArgsConstructor
@Tag(name = "Опросы")
@Slf4j
public class SurveyController {

    private final SurveyService surveyService;

    private final SurveyMapper surveyMapper;

    @Operation(
            summary = "Добавление опроса")
    @PostMapping
    public ResponseEntity<?> addSurvey(@RequestBody CreateSurveyRequest createSurveyRequest) {
        Survey survey = surveyMapper.createSurveyRequestToSurvey(createSurveyRequest);
        log.info(survey.getTitle());
        surveyService.createSurvey(survey);
        return ResponseEntity.ok("");
    }
//
//    @Operation(
//            summary = "Редактирование опроса")
//    @PutMapping
//    public ResponseEntity<?> updateSurvey(
//            @RequestBody UpdateSurveyRequest updateSurveyRequest) {
//        surveyService.updateSurvey(surveyMapper.updateSurveyRequestToSurvey(updateSurveyRequest));
//        return ResponseEntity.ok("");
//    }
//
//    @Operation(
//            summary = "Удаление опроса")
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deleteSurvey(
//            @PathVariable Long id) {
//        surveyService.removeSurvey(id);
//        return ResponseEntity.ok("");
//    }
//
    @Operation(
            summary = "Получить 1 опрос")
    @GetMapping("{id}")
    public ResponseEntity<SurveyResponse> getSurvey(
            @PathVariable Long id
    ) {
        log.info("Start survey query");
        Survey survey = surveyService.getSurvey(id);
        log.info("End survey query");

        SurveyResponse surveyResponse = surveyMapper.surveyToSurveyResponse(survey);
        return ResponseEntity.ok(surveyResponse);
    }

    @Operation(
            summary = "Получить все опросы")
    @GetMapping
    public ResponseEntity<List<SurveyResponse>> getSurveys() {
        List<SurveyResponse> surveyResponses = surveyMapper.surveyListToSurveyResponseList(surveyService.getSurvey());
        return ResponseEntity.ok(surveyResponses);
    }
}
