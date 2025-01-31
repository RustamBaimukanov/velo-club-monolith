package com.work.veloclub.controller;

import com.work.veloclub.mapper.event.EventMapper;
import com.work.veloclub.mapper.team.TeamMapper;
import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventCreateDTO;
import com.work.veloclub.model.event.EventGetFilter;
import com.work.veloclub.model.feedback.FeedbackDto;
import com.work.veloclub.service.feedback.FeedbackService;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedbacks")
@RequiredArgsConstructor
@Tag(name = "Отзывы", description = "Отзывы")
@Slf4j
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(
            summary = "Добавление отзыва",
            description = "Пользователь пишет отзыв",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь написал отзыв"),
            }
    )
    @PostMapping
    public ResponseEntity<?> addFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackService.createFeedback(feedbackDto);
        return ResponseEntity.ok("Отзыв создан");
    }

    @Operation(
            summary = "Получение всех отзывов",
            description = ""
    )
    @GetMapping
    public ResponseEntity<?> getFeedbacks() {
        //Можно написать маппер, но мне было лень
        return ResponseEntity.ok(feedbackService.getFeedback());
    }

    @Operation(
            summary = "Получение отзыва по id",
            description = "Не знаю зачем, но пусть будет"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedback(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedback(id));
    }
}
