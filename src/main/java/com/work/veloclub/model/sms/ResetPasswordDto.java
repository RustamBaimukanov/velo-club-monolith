package com.work.veloclub.model.sms;

public record ResetPasswordDto(String phoneNumber, String code, String newPassword) {
}
