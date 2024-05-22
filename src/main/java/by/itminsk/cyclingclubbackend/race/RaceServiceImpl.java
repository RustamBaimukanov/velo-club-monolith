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
    public void createRace(RaceDto raceDto) {
        Race race = Race.builder()
                .name(raceDto.getName())
                .routeStartPoint(raceDto.getRouteStartPoint())
                .routeEndPoint(raceDto.getRouteEndPoint())
                .build();
        raceRepository.save(race);
    }

    @Override
    public void updateRace(RaceDto raceDto) {
        //TODO добавить обработку, есть ли такой маршрут
        Race race = Race.builder()
                .id(raceDto.getId())
                .name(raceDto.getName())
                .routeStartPoint(raceDto.getRouteStartPoint())
                .routeEndPoint(raceDto.getRouteEndPoint())
                .build();
        raceRepository.save(race);
    }

    @Override
    public RaceDto getRace(Long id) {
        Race race = raceRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Маршрут не найден."));
        return RaceDto.builder()
                .id(race.getId())
                .name(race.getName())
                .routeStartPoint(race.getRouteStartPoint())
                .routeEndPoint(race.getRouteEndPoint())
                .build();
    }

    @Override
    public List<RaceDto> getRace(Boolean isRelevant) {
        //TODO is relevant - лучшие маршруты, какой критерии определяет лучший маршрут?
        return raceRepository.findAll().stream().map(race -> RaceDto.builder()
                .id(race.getId())
                .name(race.getName())
                .routeStartPoint(race.getRouteStartPoint())
                .routeEndPoint(race.getRouteEndPoint()).build()).collect(Collectors.toList());
    }
}
