package by.itminsk.cyclingclubbackend.controller.sms;

import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.sms.SmsDto;
import by.itminsk.cyclingclubbackend.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class SmsController {


    @Autowired
    private SmsService smsService;

    @PostMapping("/verify-user")
    public ResponseEntity<?> verifyUser (@RequestBody SmsDto smsDto)
    {
        return  smsService.verifySmsCode(smsDto);
    }

}
