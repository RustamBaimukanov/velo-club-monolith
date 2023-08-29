package by.itminsk.cyclingclubbackend.controller.user;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/info")
    public User getCities(@RequestBody LoginDto loginDto, HttpServletRequest request){
        return userService.getUser(loginDto.getTel());
    }
}
