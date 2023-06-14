package by.itminsk.cyclingclubbackend.service.sms;

import by.itminsk.cyclingclubbackend.model.sms.SmsDto;
import org.springframework.http.ResponseEntity;

public interface SmsService {

    ResponseEntity<?> verifySmsCode(SmsDto smsDto);
}
