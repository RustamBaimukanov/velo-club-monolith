package com.work.veloclub.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@EqualsAndHashCode
public class UserMenuDTO {

    @JsonIgnore
    Long id;

    String firstName;

    String lastName;

    byte[] photo;


    public UserMenuDTO(Long id, String firstName, String lastName, byte[] photo) {
        this.id = id;

        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }


}
