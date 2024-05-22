package by.itminsk.cyclingclubbackend.role.dto;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
public class RoleDto {

    private Long id;

    private RoleEnum name;

    private String qualification;


    public RoleDto(Long id, RoleEnum name, String qualification) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
    }
}
