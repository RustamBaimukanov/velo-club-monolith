package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.user.dto.LoginDto;
import by.itminsk.cyclingclubbackend.event.EventResult;
import by.itminsk.cyclingclubbackend.trophy.TrophyService;
import by.itminsk.cyclingclubbackend.trophy.Trophy;
import by.itminsk.cyclingclubbackend.user.dto.EditUserDTO;
import by.itminsk.cyclingclubbackend.user.dto.UpdateUserDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserInfoDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserMenuDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final TrophyService trophyService;

    @Operation(
            summary = "Редактирование пользователя админом",
            description = "API редактирования пользователя админом."
    )
    @PutMapping(value = "/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editUserByAdmin(@RequestParam(defaultValue = "1") Long id, @ModelAttribute UpdateUserDTO updateUserDTO) {
        log.info(String.valueOf(updateUserDTO.getQualification()));
        log.info(String.valueOf(updateUserDTO.getImageStatus()));
        return userService.editUserByAdmin(id, updateUserDTO);

    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> getUser(
            @RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(userService.getUserProfile(id));
        }
        else {
            return ResponseEntity.ok(userService.getUser());
        }

    }

    @GetMapping(value = "/user/participants")
    public ResponseEntity<?> getParticipants(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) RoleEnum exceptedRole) {
        if (id != null) {
            return ResponseEntity.ok(userService.getUser(id));
        } else if (exceptedRole != null) {
            return ResponseEntity.ok(userService.getUsersExceptRole(exceptedRole));
        } else {
            return ResponseEntity.ok(userService.getUser());
        }

    }

    @GetMapping("/user/info")
    public ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(userService.getUserInfo(currentPrincipalName));
    }

    @GetMapping("/user/edit")
    public EditUserDTO getEditableUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + ":::" + authorities);
        return userService.getEditableUser(currentPrincipalName);
    }

    @PostMapping("/user/edit")
    public ResponseEntity<?> postEditableUser(@ModelAttribute UpdateUserDTO updateUserDTO) {
        return userService.editUser(updateUserDTO);

    }

    @GetMapping("/user/menu")
    public UserMenuDTO getUserMenu() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + ":::" + authorities);
        return userService.getUserMenu(currentPrincipalName);
    }

    @GetMapping("/user/event")
    public Map<Integer, List<EventResult>> getEventByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        try {
            return userService.getUserInfo(currentPrincipalName).getEvent();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/user/trophies")
    public Set<Trophy> getTrophiesByUser(@RequestBody(required = false) LoginDto loginDto) {
        if (loginDto == null)
            return trophyService.findAllByPhoneNumber("+375259999903");
        if (loginDto.getTel() == null)
            return trophyService.findAllByPhoneNumber("+375259999903");
        try {
            return
                    trophyService.findAllByPhoneNumber(loginDto.getTel());
        } catch (Exception e) {
            return null;
        }
    }

}
