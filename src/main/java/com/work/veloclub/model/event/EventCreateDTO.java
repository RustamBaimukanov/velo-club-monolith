package com.work.veloclub.model.event;

import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.model.user.GenderEnum;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public record EventCreateDTO(@NotBlank(message = "Название мероприятия не может быть пустым")
                             @Size(max = 255, message = "Название мероприятия не может превышать 255 символов")
                             String name,

                             @Size(max = 4000, message = "Описание мероприятия не может превышать N символов")
                             String description,
                             LocalDate birthDateFrom,

                             LocalDate birthDateTo,

                             //TODO пока нет в форме, не валидируется
                             LocalDateTime startDate,

                             //TODO пока нет в форме, не валидируется
                             LocalDateTime endDate,

                             @NotNull(message = "Не выбран маршрут")
                             Long bestRoute,

                             @NotNull(message = "Не указано местоположение для мероприятия")
                             Long city,

                             @NotNull(message = "Не указана классификация")
                             Long categoryId,

                             @NotNull(message = "Не выбрано ограничение по полу")
                             GenderEnum gender,

                             @NotNull(message = "Не выбрана категория участников")
                             RolesEnum participantsCategory,

                             List<Long> participants



) {


}
