package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.user.dto.LoginDto;
import by.itminsk.cyclingclubbackend.event.EventResult;
import by.itminsk.cyclingclubbackend.trophy.TrophyService;
import by.itminsk.cyclingclubbackend.trophy.Trophy;
import by.itminsk.cyclingclubbackend.user.dto.EditUserDTO;
import by.itminsk.cyclingclubbackend.user.dto.UpdateUserDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserInfoDTO;
import by.itminsk.cyclingclubbackend.user.dto.UserMenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final TrophyService trophyService;


    @GetMapping("/info")
    public UserInfoDTO getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + ":::" + authorities);
        return userService.getUserInfo(currentPrincipalName);
    }

    @GetMapping("/edit")
    public EditUserDTO getEditableUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + ":::" + authorities);
        return userService.getEditableUser(currentPrincipalName);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> postEditableUser(@ModelAttribute UpdateUserDTO updateUserDTO) {
        return userService.editUser(updateUserDTO);

    }

    @GetMapping("/menu")
    public UserMenuDTO getUserMenu() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + ":::" + authorities);
        return userService.getUserMenu(currentPrincipalName);
    }

    @GetMapping("/event")
    public Map<Integer, List<EventResult>> getEventByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        try {
            return userService.getUserInfo(currentPrincipalName).getEvent();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/trophies")
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