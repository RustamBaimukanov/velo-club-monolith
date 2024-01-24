package by.itminsk.cyclingclubbackend.event.dto;

import lombok.*;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class EventBlockDTO {

    private String name;

    private Date date;

    private Map<String, Integer> rating;

    public EventBlockDTO(String name, Date date) {
        this.name = name;
        this.date = date;
    }
}
