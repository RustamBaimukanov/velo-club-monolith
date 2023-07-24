package by.itminsk.cyclingclubbackend.controller.restore;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.exception_handler.RestoreUserNotFound;
import by.itminsk.cyclingclubbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/restore/")
public class RestoreController {

    @Autowired
    private UserService userService;

    @PostMapping("/restore-password")
    public ResponseEntity<?> restorePassword(@RequestBody LoginDto loginDto) throws RestoreUserNotFound {
        return  userService.restorePassword(loginDto);
    }


}
