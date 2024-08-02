package by.itminsk.cyclingclubbackend.race.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceDto {

    private Long id;

    @NotBlank(message = "Название маршрута не может быть пустым")
    @Size(max = 255,message = "Название маршрута не может превышать 255 символов")
    private String name;

    @Size(max = 255,message = "Наименование начальной точки маршрута не может превышать 255 символов")
    private String routeStartPoint;

    @Size(max = 255,message = "Наименование конечной точки маршрута не может превышать 255 символов")
    private String routeEndPoint;
}
