package by.itminsk.cyclingclubbackend.controller.user;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.model.user.EventResult;
import by.itminsk.cyclingclubbackend.model.user.Trophy;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.model.user.UserInfoDTO;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.trophy.TrophyService;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrophyService trophyService;


    @PostMapping("/user/info")
    public UserInfoDTO getUser(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return userService.getUserInfo(loginDto.getTel());
    }

    @PostMapping("/user/event")
    public Map<Integer, List<EventResult>> getEventByUser(@RequestBody(required = false) LoginDto loginDto) {
        if (loginDto == null) return userService.getUserInfo("+375251111111").getEvent();
        if (loginDto.getTel() == null) return userService.getUserInfo("+375251111111").getEvent();
        try {
            return userService.getUserInfo(loginDto.getTel()).getEvent();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/user/trophies")
    public Set<Trophy> getTrophiesByUser(@RequestBody(required = false) LoginDto loginDto) {
        if (loginDto == null)
            return trophyService.findAllByPhoneNumber("+375259999903");
        if (loginDto.getTel() == null)
            return trophyService.findAllByPhoneNumber("+375259999903");
        try {
            return
                    trophyService.findAllByPhoneNumber(loginDto.getTel());
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/user/event")
    public Map<Integer, List<EventResult>> getEvent() {

        return userService.getUserInfo("+375251111111").getEvent();

    }
}
