package by.itminsk.cyclingclubbackend.race;

import by.itminsk.cyclingclubbackend.race.dto.RaceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RaceMapper {

    RaceDto toRaceDto(Race race);

    List<RaceDto> toRaceDtoList(List<Race> races);

    Race fromRaceDto(RaceDto raceDto);

    List<Race> fromRaceDtoList(List<RaceDto> races);
}
