package com.work.veloclub.model.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAnswerRequest(

        Long id,  // id может быть null для новых ответов

        @NotNull(message = "Поле ownOption не должно быть пустым")
        Boolean ownOption,

        @NotBlank(message = "Ответ не должен быть пустым")
        @Size(max = 50, message = "Длина ответа не должна превышать 50 символов")
        String answer

) {
}
