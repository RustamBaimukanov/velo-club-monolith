package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.user.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.user.dto.UpdateUserDTO;
import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.r_city.CityService;
import by.itminsk.cyclingclubbackend.role.RoleService;
import by.itminsk.cyclingclubbackend.team.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> register (@ModelAttribute RegisterByAdminDto registerDto, HttpServletResponse response) throws IOException {
        return  userService.registerByAdmin(registerDto);
    }


}
