package by.itminsk.cyclingclubbackend.controller.user;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.trophy.TrophyService;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrophyService trophyService;


    @GetMapping("/user/info")
    public UserInfoDTO getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + ":::" + authorities);
        return userService.getUserInfo(currentPrincipalName);
    }

    @GetMapping("/user/edit")
    public EditUserDTO getEditableUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + ":::" + authorities);
        return userService.getEditableUser(currentPrincipalName);
    }

//    @PostMapping("/user/edit")
//    public ResponseEntity<?> postEditableUser(@ModelAttribute UpdateUserDTO updateUserDTO) {
//        return userService.editUser(updateUserDTO);
//
//    }

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

//    @GetMapping("/user/event")
//    public Map<Integer, List<EventResult>> getEvent() {
//
//        return userService.getUserInfo("+375251111111").getEvent();
//
//    }
}
