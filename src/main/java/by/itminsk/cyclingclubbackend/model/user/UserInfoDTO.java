package by.itminsk.cyclingclubbackend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
public class UserInfoDTO {

    @JsonIgnore
    Long id;

    String email;

    String phoneNumber;

    String firstName;

    String lastName;

    Date birthDate;

    String sex;

    Double height;

    Double weight;

    String address;

    byte[] photo;

    String photoFormat;

    Set<SocialNetworkDTO> socialNetworks;

    Set<EventResult> eventResults;


    Team team;

    City city;

    Map<Integer, List<EventResult>> event;


    public UserInfoDTO(Long id, String email, String phoneNumber, String firstName, String lastName, Date birthDate, String sex, Double height, Double weight, String address, byte[] photo, String photoFormat, Team team, City city) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.photo = photo;
        this.photoFormat = photoFormat;
        this.team = team;
        this.city = city;
    }

    //Set<EventResult> eventResults;



}