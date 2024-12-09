package com.work.veloclub.mapper.race;

import com.work.veloclub.model.race.Race;
import com.work.veloclub.model.race.RaceResponse;

import java.util.List;

public class RaceMapper {

    public static RaceResponse mapToRaceResponse(Race race) {
        return new RaceResponse(race.getId(), race.getName(), race.getRouteStartPoint(), race.getRouteEndPoint(), race.getDistance(), race.getAverageSpeed(), race.getCalories());
    }

    public static List<RaceResponse> mapToRaceResponse(List<Race> races){
        return races.stream().map(RaceMapper::mapToRaceResponse).toList();
    }
}
