package com.work.veloclub.model.event;

import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.event.category.CategoryDTO;
import com.work.veloclub.model.user.GenderEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventListDTO(Long id, String name, LocalDateTime startDate, LocalDateTime endDate, GenderEnum availableGender, CategoryDTO category) {
}
