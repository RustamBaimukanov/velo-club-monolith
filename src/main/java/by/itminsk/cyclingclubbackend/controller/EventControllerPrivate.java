package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.EventService;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private")
@RequiredArgsConstructor
@Tag(name = "Мероприятия", description = "Операции связанные с ивентами")
public class EventControllerPrivate {

    private final EventService eventService;

    @Operation(
            summary = "Добавление мероприятия",
            description = "API добавления мероприятия(будет дополнено, после обсуждении)."
    )
    @PostMapping(value = "/event", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addEvent(@ModelAttribute EventPostDto event) {
        eventService.createEvent(event);
        return ResponseEntity.ok("");
    }

    @ResponseBody
    @PostMapping(value = "/event-test")
    public ResponseEntity<?> addEventTest(@RequestBody EventPostDto event) {
        eventService.createEvent(event);
        return ResponseEntity.ok("");
    }


}
