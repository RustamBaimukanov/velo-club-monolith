package com.work.veloclub.model.race;

public record RaceResponse(
        Long id,
        String name,
        String routeStartPoint,
        String routeEndPoint,

        Double distance,
        Double averageSpeed,
        Double calories

) {
}
