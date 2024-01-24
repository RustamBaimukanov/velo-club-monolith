package by.itminsk.cyclingclubbackend.event;

import by.itminsk.cyclingclubbackend.event.dto.EventBlockDTO;

public interface EventService {

    EventBlockDTO getEvent(Long id);
}
