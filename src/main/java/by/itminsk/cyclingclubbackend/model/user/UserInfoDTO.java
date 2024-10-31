package by.itminsk.cyclingclubbackend.model.user;

import by.itminsk.cyclingclubbackend.model.city.City;
import by.itminsk.cyclingclubbackend.model.event_result.EventResult;
import by.itminsk.cyclingclubbackend.model.social_network.SocialNetworkDTO;
import by.itminsk.cyclingclubbackend.model.team.Team;
import by.itminsk.cyclingclubbackend.model.trophy.Trophy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
@EqualsAndHashCode
public class UserInfoDTO {

    Long id;

    String email;

    String phoneNumber;

    String firstName;

    String lastName;

    LocalDate birthDate;

    GenderEnum sex;

    byte[] photo;

    String photoFormat;

    Set<SocialNetworkDTO> socialNetworks;

    @JsonIgnore
    Set<EventResult> eventResults;

    Set<Trophy> trophies;

    Team team;

    City city;

    Map<Integer, List<EventResult>> event;

    String qualification;

    private Double height;

    private Double weight;


    public UserInfoDTO(Long id, String email, String phoneNumber, String firstName, String lastName, LocalDate birthDate, GenderEnum sex, byte[] photo , String photoFormat, Team team, City city, Double height, Double weight) {
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
        this.photoFormat = photoFormat;
        this.height = height;
        this.weight = weight;
    }

    //Set<EventResult> eventResults;



}
