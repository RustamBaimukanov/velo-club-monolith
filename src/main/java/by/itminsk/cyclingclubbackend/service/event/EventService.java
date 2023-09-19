package by.itminsk.cyclingclubbackend.service.event;

import by.itminsk.cyclingclubbackend.model.eventdto.EventBlockDTO;

public interface EventService {

    EventBlockDTO getEvent(Long id);
}
