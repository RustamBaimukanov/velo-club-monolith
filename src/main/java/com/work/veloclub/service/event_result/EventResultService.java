package com.work.veloclub.service.event_result;

import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.event_result.EventResultCreateRequest;
import com.work.veloclub.model.event_result.EventResultUpdateRequest;

import java.util.List;

public interface EventResultService {

    EventResult getEventResultsById(Long id);

    List<EventResult> getEventResultsByEventId(Long id);

    EventResult createEventResult(Long id, EventResultCreateRequest eventResult);

    EventResult updateEventResult(Long id, EventResultUpdateRequest eventResult);

}
