package by.itminsk.cyclingclubbackend.controller.auth;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import by.itminsk.cyclingclubbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto)
    { return  userService.register(registerDto);}

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@RequestBody RegisterDto registerDto)
    { return  userService.register(registerDto);}

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto)
    { return  userService.authenticate(loginDto);}

    @GetMapping("/xxx")
    public String main(){
        return "IS Authenticated";
    }



}
