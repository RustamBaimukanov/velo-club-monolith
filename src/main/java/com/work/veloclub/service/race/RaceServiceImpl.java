package com.work.veloclub.service.race;

import com.work.veloclub.model.race.Race;
import com.work.veloclub.model.race.RaceCreateRequest;
import com.work.veloclub.model.race.RaceUpdateRequest;
import com.work.veloclub.repository.race.RaceRepository;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    //Только для отладки
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Race createRace(RaceCreateRequest raceCreateRequest) {
        Race race = new Race();
        race.setName(raceCreateRequest.name());
        race.setRouteStartPoint(raceCreateRequest.routeStartPoint());
        race.setRouteEndPoint(raceCreateRequest.routeEndPoint());
        race.setDistance(raceCreateRequest.distance());
        race.setCalories(raceCreateRequest.calories());
        race.setAverageSpeed(raceCreateRequest.averageSpeed());
        log.info("Гонка:" + entityManager.contains(race));
        raceRepository.save(race);
        return race;
    }

    @Override
    public List<Race> createRaceGenerate(List<RaceCreateRequest> raceCreateRequest) {
        List<Race> raceList = raceCreateRequest.stream().map(createRequest -> {
            Race race = new Race();
            race.setName(createRequest.name());
            race.setRouteStartPoint(createRequest.routeStartPoint());
            race.setRouteEndPoint(createRequest.routeEndPoint());
            race.setDistance(createRequest.distance());
            race.setCalories(createRequest.calories());
            race.setAverageSpeed(createRequest.averageSpeed());
            return race;
        }).toList();
        raceRepository.saveAll(raceList);
        return raceList;
    }

    @Override
    @Transactional
    public Race updateRace(Long id, RaceUpdateRequest raceUpdateRequest) {
        //TODO добавить обработку, есть ли такой маршрут
        Race race = raceRepository.findById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.RaceErrors.NOT_FOUND));
        race.setName(raceUpdateRequest.name());
        race.setRouteStartPoint(raceUpdateRequest.routeStartPoint());
        race.setRouteEndPoint(raceUpdateRequest.routeEndPoint());
        race.setDistance(raceUpdateRequest.distance());
        race.setCalories(raceUpdateRequest.calories());
        race.setAverageSpeed(raceUpdateRequest.averageSpeed());
        log.info("Гонка:" + entityManager.contains(race));
        return race;
    }

    @Override
    public Race getRace(Long id) {
        return raceRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Маршрут не найден."));
    }

    @Override
    public List<Race> getRace(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Race> race = raceRepository.findAll(pageable);
        return race.getContent();
    }

    @Override
    public Race findRaceById(Long id) {
        return raceRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Маршрут не найден."));
    }

    @Override
    public void raceExistenceValidator(Long id) {
        if (!raceRepository.existsById(id)) throw new ObjectNotFound("Маршрут не найден.");
    }
}
