package by.itminsk.cyclingclubbackend.race;

import by.itminsk.cyclingclubbackend.race.dto.RaceDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RaceService {

    void createRace(RaceDto raceDto);

    void updateRace(RaceDto raceDto);

    RaceDto getRace(Long id);

    List<RaceDto> getRace(Boolean isRelevant);
}
