package com.work.veloclub.service.event_result;

import com.work.veloclub.model.event_result.EventResult;
import com.work.veloclub.model.event_result.EventResultCreateRequest;
import com.work.veloclub.model.event_result.EventResultUpdateRequest;

import java.util.List;

//TODO реализовать сохранение результатов списком
public interface EventResultService {

    /**
     * Отдает результат мероприятия по id
     * @param id id результата
     * @return возвращает результат мероприятия
     */
    EventResult getEventResultsById(Long id);

    /**
     * Отдает список результатов мероприятия по id мероприятия
     * @param id id результата
     * @return возвращает результаты мероприятия
     */
    List<EventResult> getEventResultsByEventId(Long id);

    /**
     * Сохраняет результат мероприятия и возвращает его в ответе
     * @param id id мероприятия
     * @param eventResult данные о его результатах
     * @return возвращает сохраненный результат мероприятия
     */
    EventResult createEventResult(Long id, EventResultCreateRequest eventResult);

    /**
     * Обновляет результат мероприятия и возвращает его в ответе
     * @param id id мероприятия
     * @param eventResult данные о его результатах
     * @return возвращает обновленный результат мероприятия
     */
    EventResult updateEventResult(Long id, EventResultUpdateRequest eventResult);

}
