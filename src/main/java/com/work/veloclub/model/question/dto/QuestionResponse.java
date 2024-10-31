package com.work.veloclub.model.question.dto;

import com.work.veloclub.model.answer.dto.AnswerResponse;

import java.util.List;

public record QuestionResponse(

        Long id,
        String question,
        Boolean allowMultipleAnswer,
        Boolean isRequired,
        List<AnswerResponse> answers) {
}
