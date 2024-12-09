package com.work.veloclub.service.race;

import com.work.veloclub.model.race.Race;
import com.work.veloclub.model.race.RaceCreateRequest;
import com.work.veloclub.model.race.RaceUpdateRequest;

import java.util.List;

public interface RaceService {

    Race createRace(RaceCreateRequest createRequest);

    //REMOVAL
    List<Race> createRaceGenerate(List<RaceCreateRequest> createRequest);


    Race updateRace(Long id, RaceUpdateRequest race);

    Race getRace(Long id);

    List<Race> getRace(Integer page, Integer size);


    Race findRaceById(Long id);

    void raceExistenceValidator(Long id);
}
