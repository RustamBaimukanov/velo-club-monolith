package by.itminsk.cyclingclubbackend.sms;

import by.itminsk.cyclingclubbackend.sms.dto.SmsDto;
import org.springframework.http.ResponseEntity;

public interface SmsService {

    ResponseEntity<?> verifySmsCode(SmsDto smsDto);
}
