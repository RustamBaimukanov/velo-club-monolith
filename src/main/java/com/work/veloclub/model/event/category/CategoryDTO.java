package com.work.veloclub.model.event.category;

import lombok.Builder;

@Builder
public record CategoryDTO(Long id, EventRaceType type, String name, String numericProperty,
                          String descriptionProperty) {

}
