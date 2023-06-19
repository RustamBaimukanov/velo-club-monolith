package by.itminsk.cyclingclubbackend.controller.test;

import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String main(){
        return "IS jklfjdsklfmjs";
    }

    @GetMapping("/test/users")
    public List<User> getUsers(){
        return userService.getAll();
    }


}


