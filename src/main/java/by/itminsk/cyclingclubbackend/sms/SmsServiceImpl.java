package by.itminsk.cyclingclubbackend.sms;

import by.itminsk.cyclingclubbackend.sms.dto.SmsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public ResponseEntity<?> verifySmsCode(SmsDto smsDto) {
        if (smsDto.getCode() == null){
            return new ResponseEntity<>("Код не введен   ", HttpStatus.NOT_ACCEPTABLE);
        }
        else if (smsDto.getCode() == 1111) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("Неверный КОД   ", HttpStatus.NOT_ACCEPTABLE);
    }
}
