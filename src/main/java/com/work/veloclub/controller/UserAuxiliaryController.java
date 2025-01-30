package com.work.veloclub.controller;

import com.work.veloclub.model.sms.ResetPasswordDto;
import com.work.veloclub.model.sms.ResetPhoneDto;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user.UserInfoDTO;
import com.work.veloclub.model.user.UserPasswordDto;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователь|Вспомогательные функции")
public class UserAuxiliaryController {

    private final UserService userService;

    @PostMapping("/forgot-password")
    @Operation(
            summary = "Забыли пароль",
            description = "Пользователь отправляет запрос на смену пароля, получает код по которому он сможет поменять пароль в api/users/reset-password"
    )
    public ResponseEntity<?> forgotPassword(@RequestParam String phoneNumber) {

        userService.findUserByPhoneNumber(phoneNumber);

        //smsService.sendSms(phoneNumber, "Ваш код для сброса пароля: " + code);

        return ResponseEntity.ok("Код отправлен на ваш номер телефона");
    }

    @PostMapping("/reset-password")
    @Operation(
            summary = "Изменение пароля(через алгоритм с смс подтверждением)",
            description = "Пользователь меняет пароль не входя в систему с помощью смс подтверждения."
    )
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        //Проверка временного кода ===>
        if (!resetPasswordDto.code().equals("1111")) return ResponseEntity.ok("Неверный код");
        return userService.changePassword(resetPasswordDto);
    }

    @PostMapping("/change-password")
    @Operation(
            summary = "Изменение пароля(напрямую когда пользователь находится в системе(вероятно находится в окне редактирования профиля)",
            description = "Пользователь меняет пароль."
    )
    public ResponseEntity<?> change(@RequestBody UserPasswordDto userPasswordDto) {
        //Проверка временного кода ===>

        return userService.changePassword(userPasswordDto);
    }

    @PostMapping("/change-phone-number")
    @Operation(
            summary = "Изменение телефона(через алгоритм с смс подтверждением, при этом пользователь находится в системе)",
            description = "Пользователь меняет номер телефона(номер телефона является частью токена, поэтому возможны сайд эффекты)"
    )
    public ResponseEntity<?> resetPhoneNumber(@RequestBody ResetPhoneDto resetPhoneDto) {
        //Проверка временного кода ===>
        return userService.changePhoneNumber(resetPhoneDto);
    }
}

