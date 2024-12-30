package com.work.veloclub.mapper.event;

import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventGetDTO;
import com.work.veloclub.model.event.EventListDTO;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.role.RolesEnum;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventMapper {

    public static EventGetDTO mapToEventDto(Event event){
        Function<Set<RoleEnum>, RolesEnum> convert = x -> x.contains(RoleEnum.SPORTSMAN)
                && x.contains(RoleEnum.DABBLER)
                ? RolesEnum.ANY
                : x.contains(RoleEnum.SPORTSMAN)
                ? RolesEnum.SPORTSMAN
                : RolesEnum.DABBLER;

        return EventGetDTO.builder()
                .name(event.getName())
                .description(event.getDescription())
                .birthDateFrom(event.getBirthDateFrom())
                .birthDateTo(event.getBirthDateTo())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .bestRoute(event.getRace().getId())
                .city(event.getCity().getId())
                .gender(event.getAvailableGender())
                .participantsCategory(convert.apply(event.getAvailableRoles().stream().map(Role::getName).collect(Collectors.toSet())))
                .build();
    }

    public static List<EventGetDTO> mapToEventDto(List<Event> event){
        return event.stream().map(EventMapper::mapToEventDto).toList();

    }

    public static EventListDTO mapToEventListDto(Event event) {
        return EventListDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .availableGender(event.getAvailableGender())
                .category(CategoryMapper.mapToCategoryDTO(event.getCategory()))
                .build();
    }
    public static List<EventListDTO> mapToEventListDto(List<Event> events) {
        return events.stream().map(EventMapper::mapToEventListDto).toList();
    }
}
