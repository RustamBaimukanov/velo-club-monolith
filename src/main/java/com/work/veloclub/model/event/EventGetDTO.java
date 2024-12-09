package com.work.veloclub.model.event;

import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.model.user.GenderEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventGetDTO(String name,

                          String description,
                          LocalDate birthDateFrom,

                          LocalDate birthDateTo,

                          LocalDateTime startDate,

                          LocalDateTime endDate,

                          Long bestRoute,

                          Long city,

                          GenderEnum gender,

                          RolesEnum participantsCategory,

                          List<Long> participants) {
}
