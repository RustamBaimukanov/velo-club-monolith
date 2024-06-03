package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.sms.SmsService;
import by.itminsk.cyclingclubbackend.sms.dto.SmsDto;
import by.itminsk.cyclingclubbackend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sms")
@RequiredArgsConstructor
public class SmsController {


    private final SmsService smsService;

    private final UserService userService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser (@RequestBody SmsDto smsDto)
    {
        return  smsService.verifySmsCode(smsDto);
    }

    @PostMapping("/check-tel-restore")
    public ResponseEntity<?> checkTelExiting(@RequestBody SmsDto smsDto)
    {
        return userService.existByPhoneNumber(smsDto.getTel()) ?
            new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>("Такой номер телефона не зарегистрирован.", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/check-tel-signup")
    public ResponseEntity<?> checkTelExitingSignUp(@RequestBody SmsDto smsDto)
    {
        return userService.existByPhoneNumber(smsDto.getTel()) ?
                new ResponseEntity<>("Такой номер телефона уже зарегистрирован.", HttpStatus.NOT_ACCEPTABLE) :
                new ResponseEntity<>(HttpStatus.OK);
    }

}
