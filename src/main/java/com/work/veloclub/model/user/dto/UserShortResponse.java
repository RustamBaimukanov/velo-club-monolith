package com.work.veloclub.model.user.dto;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.city.CityDTO;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.social_network.SocialNetworkDTO;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.trophy.Trophy;
import com.work.veloclub.model.user.GenderEnum;

import java.time.LocalDate;
import java.util.Set;

public record UserShortResponse(Long id,

                                String firstName,

                                String lastName,
                                String surname,

                                LocalDate birthDate,

                                GenderEnum gender,

                                String team,

                                String city) {
}
