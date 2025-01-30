package com.work.veloclub.controller;

import com.work.veloclub.service.sms.SmsService;
import com.work.veloclub.model.sms.SmsDto;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.UniqueObjectExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sms")
@RequiredArgsConstructor
public class SmsController {


    private final SmsService smsService;

    private final UserService userService;

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyUser(@RequestBody SmsDto smsDto) {
        return smsService.verifySmsCode(smsDto);
    }

//    @PostMapping("/check-tel-restore")
//    public ResponseEntity<?> checkTelExiting(@RequestBody SmsDto smsDto) {
//        if (!userService.existByPhoneNumber(smsDto.getTel()))
//            throw new ObjectNotFound("Такой номер телефона не зарегистрирован.");
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("/check-tel-signup")
//    public ResponseEntity<?> checkTelExitingSignUp(@RequestBody SmsDto smsDto) {
//        if (userService.existByPhoneNumber(smsDto.getTel()))
//            throw new UniqueObjectExistException("Такой номер телефона уже зарегистрирован.");
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
