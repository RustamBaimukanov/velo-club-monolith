package com.work.veloclub.model.team;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TeamDTODeprecated {

    private Long id;

    private String name;

    public TeamDTODeprecated(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
