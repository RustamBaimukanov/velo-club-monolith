package by.itminsk.cyclingclubbackend.controller.event;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.event.EventService;
import by.itminsk.cyclingclubbackend.service.trophy.TrophyService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/event/{id}")
    public String getEvent(@PathVariable Long id) {
        return new Gson().toJson(eventService.getEvent(id));
    }

//    @GetMapping("/user/event")
//    public Map<Integer, List<EventResult>> getEvent() {
//
//        return userService.getUserInfo("+375251111111").getEvent();
//
//    }
}
