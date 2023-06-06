package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import by.itminsk.cyclingclubbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class TestController {

    @Autowired
    private UserService userService ;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto)
    { return  userService.register(registerDto);}

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto)
    { return  userService.authenticate(loginDto);}

    @GetMapping("/hi")
    public String sayHi ()
    { return "Hi" ;}

    @GetMapping("/hello")
    public String sayHello ()
    { return "Hello" ;}

}
