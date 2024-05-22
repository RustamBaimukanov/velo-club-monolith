package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.EventService;
import by.itminsk.cyclingclubbackend.event.dto.EventPostDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/private")
@RequiredArgsConstructor
public class EventControllerPrivate {

    private final EventService eventService;


    @PostMapping(value = "/event", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addEvent(@ModelAttribute EventPostDto event) {
        System.out.println(event.getEventName());
        System.out.println(event.getGender());
        return ResponseEntity.ok("");
    }


}
