package by.itminsk.cyclingclubbackend.service.sms;

import by.itminsk.cyclingclubbackend.model.sms.SmsDto;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public ResponseEntity<?> verifySmsCode(SmsDto smsDto) {
        if (smsDto.getCode() == null){
            throw  new ObjectNotFound("Код не введен.");
        }
        else if (smsDto.getCode() == 1111) return new ResponseEntity<>(HttpStatus.OK);
        throw  new ObjectNotFound("Неверный код.");
    }
}
