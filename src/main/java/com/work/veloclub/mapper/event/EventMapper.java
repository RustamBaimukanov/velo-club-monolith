package com.work.veloclub.mapper.event;

import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventPostDto;
import com.work.veloclub.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {


    //    @Mapping(target = "phoneNumber", source = "tel")
//    @Mapping(target = "birthDate", source = "birth")
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "city.id", source = "city")
//    @Mapping(target = "sex", source = "gender")
//    @Mapping(target = "role.id", constant = "3L")
    @Mapping(target = "name", source = "eventName")
    @Mapping(target = "note", source = "eventDescription")
    @Mapping(target = "availableBirthDateFrom", source = "birthDateFrom")
    @Mapping(target = "availableBirthDateTo", source = "birthDateTo")
    @Mapping(target = "availableGender", source = "gender")
    @Mapping(target = "race.id", source = "bestRoute")
    @Mapping(target = "city.id", source = "region")
    @Mapping(target = "availableUsers", source = "addParticipants", qualifiedByName = "ParticipantsToUsers")
//    @Mapping(target = "date", expression = "java(new java.util.Date())")
    Event eventDtoToEvent(EventPostDto eventPostDto);

    @Named("ParticipantsToUsers")
    default Set<User> availableUsers(List<Long> addParticipants) {
        return addParticipants != null ? addParticipants
                .stream().map(participant -> User.builder().id(participant).build()).collect(Collectors.toSet()) : null;
    }

}
