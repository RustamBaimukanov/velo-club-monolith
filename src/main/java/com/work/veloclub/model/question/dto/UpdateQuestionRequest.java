package com.work.veloclub.model.question.dto;

import com.work.veloclub.model.answer.dto.UpdateAnswerRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateQuestionRequest(

        Long id,  // id может быть null для новых вопросов

        @NotBlank(message = "Вопрос не должен быть пустым")
        @Size(max = 50, message = "Длина вопроса не должна превышать 50 символов")
        String question,

        @NotNull(message = "Поле allowMultipleAnswer не должно быть пустым")
        Boolean allowMultipleAnswer,

        @NotNull(message = "Поле isRequired не должно быть пустым")
        Boolean isRequired,

        @NotNull(message = "Список ответов не должен быть пустым")
        @Size(min = 1, message = "Вопрос должен содержать хотя бы один ответ")
        @Valid  // Валидация для списка ответов
        List<UpdateAnswerRequest> answers

) { }
