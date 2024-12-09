package com.work.veloclub.model.race;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record RaceCreateRequest(
        @NotBlank(message = "Название маршрута не может быть пустым")
        @Size(max = 255, message = "Название маршрута не может превышать 255 символов")
        String name,
        @Size(max = 255, message = "Наименование начальной точки маршрута не может превышать 255 символов")
        String routeStartPoint,
        @Size(max = 255, message = "Наименование конечной точки маршрута не может превышать 255 символов")
        String routeEndPoint,
        Double distance,
        Double averageSpeed,
        Double calories
) {
}
