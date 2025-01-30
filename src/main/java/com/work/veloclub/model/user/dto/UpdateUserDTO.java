package com.work.veloclub.model.user.dto;

import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.social_network.SocialNetworkDTO;
import com.work.veloclub.model.user.GenderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record UpdateUserDTO(
        @Email(message = "Некорректный формат адреса электронной почты.")
        @Size(max = 255, message = "Длина почты слишком велика.")
        String email,
        @Size(max = 255, message = "Длина имени слишком велика.")
        String firstName,
        @Size(max = 255, message = "Длина фамилии слишком велика.")
        String lastName,

        @Size(max = 255, message = "Длина отчества слишком велика.")
        String surname,
        @Past(message = "Дата рождения не может быть в будущем.")
        LocalDate birthDate,
        @NotNull(message = "Пол обязателен для заполнения.")
        GenderEnum gender,
        String avatar,
        Long teamId,
        Long cityId,
        RoleEnum role,
        Double height,
        Double weight,
        List<SocialNetworkDTO> socialNetworks
) {
}

