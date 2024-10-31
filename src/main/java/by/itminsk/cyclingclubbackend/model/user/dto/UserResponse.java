package by.itminsk.cyclingclubbackend.model.user.dto;

import by.itminsk.cyclingclubbackend.model.city.City;
import by.itminsk.cyclingclubbackend.model.event_result.EventResult;
import by.itminsk.cyclingclubbackend.model.role.Role;
import by.itminsk.cyclingclubbackend.model.social_network.SocialNetworkDTO;
import by.itminsk.cyclingclubbackend.model.team.Team;
import by.itminsk.cyclingclubbackend.model.trophy.Trophy;
import by.itminsk.cyclingclubbackend.model.user.GenderEnum;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record UserResponse(
        Long id,

        String email,

        String phoneNumber,

        String firstName,

        String lastName,

        LocalDate birthDate,

        GenderEnum gender,

        byte[] photo,

        String photoFormat,

        Set<SocialNetworkDTO>socialNetworks,

        Set<Trophy> trophies,

        Team team,

        City city,

        Role qualification,

        Double height,

        Double weight
) {
}
