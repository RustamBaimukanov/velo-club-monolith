package com.work.veloclub.controller;

import com.work.veloclub.mapper.user.UserMapper;
import com.work.veloclub.model.user.BearerToken;
import com.work.veloclub.model.user.LoginDto;
import com.work.veloclub.model.user.RegisterByAdminDto;
import com.work.veloclub.model.user.RegisterDto;
import com.work.veloclub.service.city.CityService;
import com.work.veloclub.service.team.TeamService;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.exception_handler.RestoreUserNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Операции связанные с авторизацией")
@Slf4j
public class AuthController {

    private final UserService userService;

    private final CityService cityService;

    private final TeamService teamService;


    @Operation(
            summary = "Регистрация пользователя",
            description = "Создает пользователя с ролью велолюбителя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь зарегистрировался, получил токен.",
                            content = @Content(schema = @Schema(implementation = BearerToken.class))),
                    @ApiResponse(responseCode = "400", description = "Пользователь ввел невалидные данные.",
                            content = @Content),
            }
    )
    @PostMapping("/register")
    public ResponseEntity<BearerToken> register(@RequestBody @Valid RegisterDto registerDto) {
        userService.uniqueUserValidator(registerDto.phoneNumber(), registerDto.email());
        cityService.cityExistenceValidator(registerDto.city());
        return userService.register(registerDto);
    }

    @Operation(
            summary = "Регистрация пользователя(DEPRECATED!!!) - использовать api/auth/register",
            description = "Создает пользователя с ролью велолюбителя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь зарегистрировался, получил токен.",
                            content = @Content(schema = @Schema(implementation = BearerToken.class))),
                    @ApiResponse(responseCode = "400", description = "Пользователь ввел невалидные данные.",
                            content = @Content),
            }
    )
    @PostMapping("/signup")
    public ResponseEntity<BearerToken> signup(@RequestBody @Valid RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @Operation(
            summary = "Регистрация пользователя администратором",
            description = "Создает пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь зарегистрирован.",
                            content = @Content(schema = @Schema(implementation = BearerToken.class))),
                    @ApiResponse(responseCode = "400", description = "Администратор ввел невалидные данные.",
                            content = @Content),
            }
    )

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/private/register")
    public ResponseEntity<?> registerByAdmin(@RequestBody @Valid RegisterByAdminDto registerDto) {
        userService.uniqueUserValidator(registerDto.phoneNumber(), registerDto.email());
        cityService.cityExistenceValidator(registerDto.city());
        teamService.teamExistenceValidator(registerDto.team());
        return userService.register(registerDto);
    }

    @Operation(
            summary = "Авторизация пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь авторизовался, получил токен.",
                            content = @Content(schema = @Schema(implementation = BearerToken.class))),
                    @ApiResponse(responseCode = "400", description = "Пользователь ввел невалидные данные.",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Пользователь ввел неверный логин или пароль",
                            content = @Content),
            }
    )
    @PostMapping("/login")
    public ResponseEntity<BearerToken> authenticate(@RequestBody @Valid LoginDto loginDto) {
        return userService.authenticate(loginDto);
    }

//    @Operation(
//            summary = "Восстановление пароля пользователя",
//            description = "API восстановления пароля пользователя."
//    )
//    @PostMapping("/restore-password")
//    public ResponseEntity<?> restorePassword(@RequestBody LoginDto loginDto) throws RestoreUserNotFound {
//        return userService.restorePassword(loginDto);
//    }

}
