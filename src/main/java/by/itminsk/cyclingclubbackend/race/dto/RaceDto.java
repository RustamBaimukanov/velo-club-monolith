package by.itminsk.cyclingclubbackend.race.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceDto {

    private Long id;

    private String name;

    private String routeStartPoint;

    private String routeEndPoint;
}
