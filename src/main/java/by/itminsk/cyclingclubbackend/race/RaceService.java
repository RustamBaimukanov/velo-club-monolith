package by.itminsk.cyclingclubbackend.race;

import by.itminsk.cyclingclubbackend.race.dto.RaceDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RaceService {

    void createRace(Race raceDto);

    void updateRace(Race race);

    Race getRace(Long id);

    List<Race> getRace(Boolean isRelevant);


    Race findRaceById(Long id);

    void raceExistenceValidator(Long id);
}
