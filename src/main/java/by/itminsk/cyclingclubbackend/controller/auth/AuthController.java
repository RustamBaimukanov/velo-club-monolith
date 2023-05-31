package by.itminsk.cyclingclubbackend.controller.auth;

import by.itminsk.cyclingclubbackend.model.login.LoginDTO;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import by.itminsk.cyclingclubbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("sign-up")
    public void signUp(UserDTO userDTO){
        userService.registration(userDTO);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(userService.authorize(loginDTO));
    }



}
