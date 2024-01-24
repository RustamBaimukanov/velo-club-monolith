package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.event.EventService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/event/{id}")
    public String getEvent(@PathVariable Long id) {
        return new Gson().toJson(eventService.getEvent(id));
    }

}
