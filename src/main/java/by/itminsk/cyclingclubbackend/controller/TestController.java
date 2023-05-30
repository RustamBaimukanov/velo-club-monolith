package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import by.itminsk.cyclingclubbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("test")
    public List<String> test() {
        return List.of("test","hello","world");
    }

    @GetMapping("create_admin")
    public void createAdmin() {
        userService.createAdmin();
    }

}
