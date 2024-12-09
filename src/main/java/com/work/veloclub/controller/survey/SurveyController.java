//package com.work.veloclub.controller.survey;
//
//import com.work.veloclub.mapper.survey.SurveyMapper;
//import com.work.veloclub.model.survey.Survey;
//import com.work.veloclub.model.survey.SurveyFilter;
//import com.work.veloclub.model.survey.dto.CreateSurveyRequest;
//import com.work.veloclub.model.survey.dto.SurveyResponse;
//import com.work.veloclub.model.survey.dto.UpdateSurveyRequest;
//import com.work.veloclub.service.survey.SurveyService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/survey")
//@RequiredArgsConstructor
//@Tag(name = "Опросы")
//@Slf4j
//public class SurveyController {
//
//    private final SurveyService surveyService;
//
//    @Operation(
//            summary = "Добавление опроса")
//    @PostMapping
//    public ResponseEntity<?> addSurvey(@Valid @RequestBody CreateSurveyRequest createSurveyRequest) {
//        Survey survey = SurveyMapper.mapToSurvey(createSurveyRequest);
//        surveyService.createSurvey(survey);
//        return ResponseEntity.ok("Опрос успешно добавлен.");
//    }
//
//    @Operation(
//            summary = "Редактирование опроса")
//    @PutMapping
//    public ResponseEntity<?> updateSurvey(@Valid @RequestBody UpdateSurveyRequest updateSurveyRequest) {
//        Survey survey = SurveyMapper.mapToSurvey(updateSurveyRequest);
//        surveyService.updateSurvey(survey);
//        return ResponseEntity.ok("");
//    }
//
//    @Operation(
//            summary = "Сброс опроса")
//    @PutMapping("/{id}/cancel")
//    public ResponseEntity<?> cancelSurvey(
//            @PathVariable Long id) {
//        surveyService.cancelSurvey(id);
//        return ResponseEntity.ok("");
//    }
//
////    @Operation(
////            summary = "Получить 1 опрос")
////    @GetMapping("{id}")
////    public ResponseEntity<SurveyResponse> getSurvey(
////            @PathVariable Long id
////    ) {
////        Survey survey = surveyService.getSurvey(id);
////
////        SurveyResponse surveyResponse = SurveyMapper.surveyToSurveyResponse(survey);
////        return ResponseEntity.ok(surveyResponse);
////    }
////
////    @Operation(
////            summary = "Получить все опросы")
////    @GetMapping
////    public ResponseEntity<List<SurveyResponse>> getSurveys(SurveyFilter filter) {
////        List<SurveyResponse> surveyResponses = SurveyMapper.surveyListToSurveyResponseList(surveyService.getSurvey(filter));
////        return ResponseEntity.ok(surveyResponses);
////    }
//}
