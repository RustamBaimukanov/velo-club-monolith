package by.itminsk.cyclingclubbackend.controller.user;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.model.user.EventResult;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.model.user.UserInfoDTO;
import by.itminsk.cyclingclubbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/info")
    public UserInfoDTO getUser(@RequestBody LoginDto loginDto, HttpServletRequest request){
        return userService.getUserInfo(loginDto.getTel());
    }

    @GetMapping("/user/event")
    public Map<Integer, List<EventResult>> getEventByUser(@RequestParam(value = "tel", required = false) String tel){
        if (tel== null) return userService.getUserInfo("+375251111111").getEvent();
        return userService.getUserInfo(tel).getEvent();
    }
}
