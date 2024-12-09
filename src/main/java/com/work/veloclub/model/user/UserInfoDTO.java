package com.work.veloclub.model.user;

import com.work.veloclub.model.city.CityDTO;
import com.work.veloclub.model.role.RoleDTO;
import com.work.veloclub.model.social_network.SocialNetworkDTO;
import com.work.veloclub.model.team.TeamDTO;
import com.work.veloclub.model.trophy.TrophyDTO;

import java.time.LocalDate;
import java.util.List;


public record UserInfoDTO(Long id,

                          String email,

                          String phoneNumber,

                          String firstName,

                          String lastName,

                          LocalDate birthDate,

                          GenderEnum gender,

                          byte[] photo,

                          String photoFormat,

                          Double height,

                          Double weight,

                          RoleDTO role,

                          CityDTO city,

                          TeamDTO team,

                          List<TrophyDTO> achievements,

                          List<SocialNetworkDTO> socialNetworks
                          ) {}
