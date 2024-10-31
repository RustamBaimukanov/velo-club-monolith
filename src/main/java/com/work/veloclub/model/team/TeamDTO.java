package com.work.veloclub.model.team;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamDTO {

    private Long id;

    private String name;

    public TeamDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
