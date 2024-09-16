package by.itminsk.cyclingclubbackend.service.race;

import by.itminsk.cyclingclubbackend.model.race.Race;

import java.util.List;

public interface RaceService {

    void createRace(Race raceDto);

    void updateRace(Race race);

    Race getRace(Long id);

    List<Race> getRace(Boolean isRelevant);


    Race findRaceById(Long id);

    void raceExistenceValidator(Long id);
}
