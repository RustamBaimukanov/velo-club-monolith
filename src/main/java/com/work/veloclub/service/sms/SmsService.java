package com.work.veloclub.service.sms;

import com.work.veloclub.model.sms.SmsDto;
import org.springframework.http.ResponseEntity;

public interface SmsService {

    ResponseEntity<?> verifySmsCode(SmsDto smsDto);
}
