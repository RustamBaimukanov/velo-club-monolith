package com.work.veloclub.model.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @Pattern(regexp = "^[+\\d](?:.*\\d)?$", message = "Формат номера телефона не верен.")
        @Size(min = 13, max = 13, message = "Некорректная длина номера телефона.")
        String phoneNumber,

        String password) {


}
