package com.work.veloclub.model.role;

import lombok.*;

@Getter
@Setter
public class RoleDtoDeprecated {

    private Long id;

    private RoleEnum name;

    private String qualification;


    public RoleDtoDeprecated(Long id, RoleEnum name, String qualification) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
    }
}
