package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.model.user.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/private")
@RequiredArgsConstructor
@Tag(name = "Админ", description = "Операции связанные с админом")
public class AdminController {

    private final UserService userService;

    @Operation(
            summary = "Добавление пользователя",
            description = "API добавления пользователя."
    )
    @PostMapping("/signup-complete")
    public ResponseEntity<?> register (@RequestBody RegisterByAdminDto registerDto) throws IOException {
        return  userService.registerByAdmin(registerDto);
    }


}
