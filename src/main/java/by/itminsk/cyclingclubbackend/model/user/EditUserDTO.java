package by.itminsk.cyclingclubbackend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
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

    Date birthDate;

    String sex;

    byte[] photo;

    String photoFormat;

    Set<SocialNetworkDTO> socialNetworks;

    Team team;

    City city;

    String qualification;

    Double height;

    Double weight;


    public EditUserDTO(Long id, String email, String phoneNumber, String firstName, String lastName, Date birthDate, String sex, byte[] photo, String photoFormat, Team team, City city, Double height, Double weight) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.photo = photo;
        this.photoFormat = photoFormat;
        this.team = team;
        this.city = city;
        this.height = height;
        this.weight = weight;
    }


}
