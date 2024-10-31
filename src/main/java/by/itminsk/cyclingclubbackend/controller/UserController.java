package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.model.user.UserInfoDTO;
import by.itminsk.cyclingclubbackend.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователь")
public class UserController {

    private final UserService userService;


    @GetMapping("/me")
    @Operation(
            summary = "Информация о пользователе",
            description = "Пользователь получает информацию о себе по токену",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о пользователе успешно получена",
                            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content)
            }
    )
    public ResponseEntity<UserInfoDTO> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(userService.getUserInfo(currentPrincipalName));
    }

}
