package com.work.veloclub.model.user;

import com.work.veloclub.util.exception_handler.custom_validator.PasswordMatches;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@PasswordMatches
public record RegisterDto(
        @Size(max = 255, message = "Длина имени слишком велика.")
        String firstName,
        @Size(max = 255, message = "Длина фамилии слишком велика.")
        String lastName,
        @Pattern(regexp = "^[+\\d](?:.*\\d)?$", message = "Формат номера телефона не верен.")
        @Size(min = 13, max = 13, message = "Некорректная длина номера телефона.")
        String phoneNumber,
        @Email(message = "Некорректный формат адреса электронной почты.")
        @Size(max = 255, message = "Длина почты слишком велика.")
        String email,
        @Size(min = 6, message = "Пароль должен содержать не менее 6 символов.")
        String password,
        String confirmPassword,
        Boolean termsAccepted,
        @Past(message = "Дата рождения не может быть в будущем.")
        LocalDate birthDate,
        @NotNull(message = "Пол обязателен для заполнения.")
        GenderEnum gender,

        @Schema(description = "ID города. Полный список доступен по запросу /api/get/cities")
        @NotNull(message = "Город обязателен для заполнения.")
        Long city) {
}