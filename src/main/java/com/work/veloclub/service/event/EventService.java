package com.work.veloclub.service.event;

import com.work.veloclub.model.event.EventCreateDTO;
import com.work.veloclub.model.event.EventCalendarDto;
import com.work.veloclub.model.event.Event;
import com.work.veloclub.model.event.EventGetFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    /**
     * Создает мероприятие
     * Если классификация мероприятия не существует, инициирует ответ с кодом 400
     * @param eventPostDto данные для сохранения мероприятия
     * @return возвращает созданное мероприятие
     */
    Event createEvent(EventCreateDTO eventPostDto);

    List<Event> createEventGenerate(List<EventCreateDTO> eventPostDto);

    /**
     * Редактирует мероприятие
     * Если классификация мероприятия не существует, инициирует ответ с кодом 400
     * @param eventPostDto данные для редактирования мероприятия
     * @return возвращает отредактированное мероприятие
     */
    Event updateEvent(Long id, EventCreateDTO eventPostDto);


//    EventBlockDTO getEvent(Long id);

    /**
     * Список мероприятии по определенной дате со связанным маршрутом
     * @param size дата
     * @param date дата
     * @return список мероприятии по определенной дате со связанным маршрутом
     */
    List<Event> getEventsByDay(Integer size, LocalDate date);

    List<Event> getEventsByMonth(LocalDate date);

    /**
     * По дате определяет месяц, затем выводит список по дням, есть или нет по этой дате мероприятия(чтобы фронтенд в календаре подсвечивал даты)
     * @param date дата
     * @return
     */
    List<EventCalendarDto> getEventCalendar(LocalDate date);

    /**
     * Получение списка мероприятии по фильтру
     * @param filter объект-фильтр
     * @return список мероприятии отфильтрованный
     */
    List<Event> getEventsByFilter(EventGetFilter filter);

    /**
     * Отдает список мероприятии по id пользователя и году
     * @param id - id пользователя
     * @param year - год
     * @return возвращает список мероприятии в которых пользователь принял, принимает или может принять участие
     * @warning список сплошной, для мобилки, надо будет разделить на чанки для оптимизации
     */
    List<Event> getEventsByUserIdAndYear(Long id, Integer year);

    /**
     * Отдает список мероприятии по id пользователя и году
     * @param id - id пользователя
     * @param year - год
     * @return возвращает список мероприятии разделенный на страницы в которых пользователь принял, принимает или может принять участие
     */
    List<Event> getEventsByUserIdAndYear(Long id, Integer year, Integer page, Integer size);



    Event getById(Long id);





    void validateCreateEventContent(EventCreateDTO eventPostDto);

    void eventExistenceValidator(Long id);

    void script();
}
