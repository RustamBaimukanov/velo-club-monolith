package by.itminsk.cyclingclubbackend.mapper.race;

import by.itminsk.cyclingclubbackend.model.race.Race;
import by.itminsk.cyclingclubbackend.model.race.RaceDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RaceMapper {

    RaceDto toRaceDto(Race race);

    List<RaceDto> toRaceDtoList(List<Race> races);

    Race fromRaceDto(RaceDto raceDto);

    List<Race> fromRaceDtoList(List<RaceDto> races);
}
