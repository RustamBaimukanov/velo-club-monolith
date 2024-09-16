package by.itminsk.cyclingclubbackend.model.role;

import lombok.*;

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
