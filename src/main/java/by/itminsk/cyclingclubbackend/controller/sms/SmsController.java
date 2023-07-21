package by.itminsk.cyclingclubbackend.controller.sms;

import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.sms.SmsDto;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class SmsController {


    @Autowired
    private SmsService smsService;

    @Autowired
    private UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser (@RequestBody SmsDto smsDto)
    {
        return  smsService.verifySmsCode(smsDto);
    }

    @PostMapping("/check-tel")
    public ResponseEntity<?> checkTelExiting(@RequestBody SmsDto smsDto)
    {
        return userService.existByPhoneNumber(smsDto.getTel()) ?
            new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>("Такой номер телефона не зарегистрирован.", HttpStatus.NOT_ACCEPTABLE);
    }

}
