package by.itminsk.cyclingclubbackend.model.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
public class RoleDto {

    private Long id;

    private String name;

    public RoleDto(Long id, String qualification) {
        this.id = id;
        this.name = qualification;
    }
}
