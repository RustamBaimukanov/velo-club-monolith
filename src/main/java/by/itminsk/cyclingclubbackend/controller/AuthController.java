package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.user.dto.LoginDto;
import by.itminsk.cyclingclubbackend.user.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.util.exception_handler.RestoreUserNotFound;
import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.r_city.CityService;
import by.itminsk.cyclingclubbackend.role.RoleService;
import by.itminsk.cyclingclubbackend.team.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Операции связанные с авторизацией")
public class AuthController {

    private final UserService userService;

    @Operation(
            summary = "Регистрация пользователя",
            description = "API регистрации пользователя."
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signup (@RequestBody RegisterDto registerDto)
    { return  userService.register(registerDto);}

    @Operation(
            summary = "Авторизация пользователя",
            description = "API авторизации пользователя."
    )
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto)
    {
        return  userService.authenticate(loginDto);
    }

    @Operation(
            summary = "Замена пароля пользователя",
            description = "API замены пароля пользователя."
    )
    @PostMapping("/restore-password")
    public ResponseEntity<?> restorePassword(@RequestBody LoginDto loginDto) throws RestoreUserNotFound {
        return  userService.restorePassword(loginDto);
    }


}
