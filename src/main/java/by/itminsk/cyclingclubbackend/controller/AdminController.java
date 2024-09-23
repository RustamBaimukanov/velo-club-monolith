package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.model.user.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.mapper.user.UserSignUpMapper;
import by.itminsk.cyclingclubbackend.service.city.CityService;
import by.itminsk.cyclingclubbackend.service.team.TeamService;
import by.itminsk.cyclingclubbackend.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/private")
@RequiredArgsConstructor
@Tag(name = "Админ", description = "Операции связанные с админом")
public class AdminController {

    private final UserService userService;

    private final CityService cityService;

    private final TeamService teamService;

    private final UserSignUpMapper userSignUpMapper;

    @Operation(
            summary = "Добавление пользователя",
            description = "API добавления пользователя."
    )
    @PostMapping("/signup-complete")
    @SneakyThrows
    public ResponseEntity<?> register(@RequestBody RegisterByAdminDto registerDto){
        userService.uniqueUserValidator(registerDto.tel(), registerDto.email());
        cityService.cityExistenceValidator(registerDto.region());
        teamService.teamExistenceValidator(registerDto.club());
        userService.registerByAdmin(userSignUpMapper.userDtoToUser(registerDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }
}
