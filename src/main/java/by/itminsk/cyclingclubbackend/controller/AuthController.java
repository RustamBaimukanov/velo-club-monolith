package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.model.user.LoginDto;
import by.itminsk.cyclingclubbackend.model.user.RegisterDto;
import by.itminsk.cyclingclubbackend.mapper.user.UserSignUpMapper;
import by.itminsk.cyclingclubbackend.service.city.CityService;
import by.itminsk.cyclingclubbackend.service.team.TeamService;
import by.itminsk.cyclingclubbackend.util.exception_handler.RestoreUserNotFound;
import by.itminsk.cyclingclubbackend.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Операции связанные с авторизацией")
@Slf4j
public class AuthController {

    private final UserService userService;

    private final CityService cityService;

    private final TeamService teamService;

    private final UserSignUpMapper userMapper;

    @Operation(
            summary = "Регистрация пользователя",
            description = "API регистрации пользователя."
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterDto registerDto) {
        userService.uniqueUserValidator(registerDto.tel(), registerDto.email());
        cityService.cityExistenceValidator(registerDto.city());
        return userService.register(userMapper.signUpDtoToUser(registerDto));
//        return userService.register(registerDto);
    }

    @Operation(
            summary = "Авторизация пользователя",
            description = "API авторизации пользователя."
    )
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        return userService.authenticate(loginDto);
    }

    @Operation(
            summary = "Замена пароля пользователя",
            description = "API замены пароля пользователя."
    )
    @PostMapping("/restore-password")
    public ResponseEntity<?> restorePassword(@RequestBody LoginDto loginDto) throws RestoreUserNotFound {
        return userService.restorePassword(loginDto);
    }


}
