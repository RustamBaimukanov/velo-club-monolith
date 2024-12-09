package com.work.veloclub.model.user;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.role.RoleDtoDeprecated;
import com.work.veloclub.model.social_network.SocialNetworkDTO;
import com.work.veloclub.model.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode
public class EditUserDTO {

    @JsonIgnore
    Long id;

    String email;

    String phoneNumber;

    String firstName;

    String lastName;

    LocalDate birthDate;

    GenderEnum sex;

    byte[] photo;

    Set<SocialNetworkDTO> socialNetworks;

    Team team;

    City city;

    RoleDtoDeprecated qualification;

    Double height;

    Double weight;


    public EditUserDTO(Long id, String email, String phoneNumber, String firstName, String lastName, LocalDate birthDate, GenderEnum sex, byte[] photo , Team team, City city, Double height, Double weight) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.photo = photo;
        this.team = team;
        this.city = city;
        this.height = height;
        this.weight = weight;
    }


}
