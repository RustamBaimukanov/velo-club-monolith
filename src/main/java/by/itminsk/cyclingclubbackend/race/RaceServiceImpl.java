package by.itminsk.cyclingclubbackend.race;

import by.itminsk.cyclingclubbackend.race.dto.RaceDto;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    @Override
    public void createRace(Race race) {
        race.setId(null);
        raceRepository.save(race);
    }

    @Override
    public void updateRace(Race race) {
        //TODO добавить обработку, есть ли такой маршрут

        raceRepository.save(race);
    }

    @Override
    public Race getRace(Long id) {
        return raceRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Маршрут не найден."));
    }

    @Override
    public List<Race> getRace(Boolean isRelevant) {
        //TODO is relevant - лучшие маршруты, какой критерии определяет лучший маршрут?
        if (isRelevant){
            return null;
        }
        else {
            return raceRepository.findAll();
        }

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
