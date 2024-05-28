package by.itminsk.cyclingclubbackend.user.dto;

import by.itminsk.cyclingclubbackend.r_city.City;
import by.itminsk.cyclingclubbackend.event.EventResult;
import by.itminsk.cyclingclubbackend.social_network.dto.SocialNetworkDTO;
import by.itminsk.cyclingclubbackend.team.Team;
import by.itminsk.cyclingclubbackend.trophy.Trophy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.*;


@Getter
@Setter
@EqualsAndHashCode
public class UserInfoDTO {

    @JsonIgnore
    Long id;

    String email;

    String phoneNumber;

    String firstName;

    String lastName;

    Date birthDate;

    GenderEnum sex;

    byte[] photo;

    Set<SocialNetworkDTO> socialNetworks;

    @JsonIgnore
    Set<EventResult> eventResults;

    Set<Trophy> trophies;

    Team team;

    City city;

    Map<Integer, List<EventResult>> event;

    String qualification;


    public UserInfoDTO(Long id, String email, String phoneNumber, String firstName, String lastName, Date birthDate, GenderEnum sex, byte[] photo , Team team, City city) {
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
    }

    //Set<EventResult> eventResults;



}
