package com.work.veloclub.model.user.dto;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.social_network.SocialNetworkDTO;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.trophy.Trophy;
import com.work.veloclub.model.user.GenderEnum;

import java.time.LocalDate;
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
