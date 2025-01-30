package com.work.veloclub.controller;

import com.work.veloclub.mapper.event.EventMapper;
import com.work.veloclub.mapper.user.UserMapper;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user.UserInfoDTO;
import com.work.veloclub.model.user.dto.UpdateUserDTO;
import com.work.veloclub.service.event.EventService;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователь")
public class UserController {

    private final UserService userService;

    private final EventService eventService;

    @PutMapping("/me")
    @Operation(
            summary = "Редактировать информацию о себе",
            description = "Пользователь обновляет свою информацию по токену",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о пользователе успешно обновлена",
                            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<UserInfoDTO> updateUserInfo(@RequestBody @Valid UpdateUserDTO updateUserDTO) {
        userService.updateMe(updateUserDTO);
        return ResponseEntity.ok(UserMapper.mapToUserInfo(userService.getMe()));
    }

    @GetMapping("/me")
    @Operation(
            summary = "Информация о пользователе",
            description = "Пользователь получает информацию о себе по токену",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о пользователе успешно получена",
                            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<UserInfoDTO> getUserInfo() {
        User user = userService.getMe();
        return ResponseEntity.ok(UserMapper.mapToUserInfo(user));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Редактировать информацию о пользователе",
            description = "Администратор обновляет информацию любого пользователя по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о пользователе успешно обновлена",
                            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<UserInfoDTO> updateUserById(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        // TODO: Реализация сервиса
        userService.updateUser(id, updateUserDTO);
        return ResponseEntity.ok(UserMapper.mapToUserInfo(userService.getMe()));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить информацию о пользователе по ID",
            description = "Администратор получает информацию о пользователе по его ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о пользователе успешно получена",
                            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<UserInfoDTO> getUserById(@PathVariable Long id) {
        // TODO: Реализация сервиса
        User user = userService.getUser(id);
        return ResponseEntity.ok(UserMapper.mapToUserInfo(user));
    }

    @GetMapping("/{id}/events-infinity")
    @Operation(
            summary = "Получить информацию о мероприятиях по пользователю",
            description = "Список мероприятии в которых пользователь потенциально может принять участие, или уже принял участие",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о мероприятиях успешно получена",
                            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<?> getUserEventsById(@PathVariable Long id, @RequestParam Integer year) {
        // TODO: Реализация сервиса
        return ResponseEntity.ok(EventMapper.mapToEventListDto(eventService.getEventsByUserIdAndYear(id, year)));
    }

    @GetMapping("/{id}/events-pages")
    @Operation(
            summary = "Получить информацию о мероприятиях по пользователю постранично",
            description = "Список мероприятии в которых пользователь потенциально может принять участие, или уже принял участие",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о мероприятиях успешно получена",
                            content = @Content(schema = @Schema(implementation = UserInfoDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<?> getUserEventsById(@PathVariable Long id, @RequestParam Integer year, @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        // TODO: Реализация сервиса
        return ResponseEntity.ok(EventMapper.mapToEventListDto(eventService.getEventsByUserIdAndYear(id, year, page, size)));
    }

    @GetMapping
    @Operation(
            summary = "Получить список пользователей",
            description = "Получить список профилей с пагинацией и фильтрацией",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserInfoDTO.class)))),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<Page<UserInfoDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String filter) {
        // TODO: Реализация сервиса
        return ResponseEntity.ok(Page.empty());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/ban")
    @Operation(
            summary = "Забанить пользователя",
            description = "Администратор банит пользователя по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь успешно забанен"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<Void> banUser(@PathVariable Long id) {
        // TODO: Реализация сервиса
        userService.banUser(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/unban")
    @Operation(
            summary = "Разбанить пользователя",
            description = "Администратор разблокирует пользователя по ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь успешно разбанен"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<Void> unbanUser(@PathVariable Long id) {
        // TODO: Реализация сервиса
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ban")
    @Operation(
            summary = "Удаление профиля",
            description = "Удаление профиля из системы(на самом деле удаления в классическом понимании нет, доступ к аккаунту блокируется, войти в него нельзя, пожалуй, все",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь успешно удален"),
                    @ApiResponse(responseCode = "401", description = "Не авторизован – неверный токен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<Void> remove() {
        // TODO: Реализация сервиса
        userService.banUser();
        return ResponseEntity.ok().build();
    }
}
