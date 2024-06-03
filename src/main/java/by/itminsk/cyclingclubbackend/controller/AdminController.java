package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.user.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.user.dto.UpdateUserDTO;
import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.r_city.CityService;
import by.itminsk.cyclingclubbackend.role.RoleService;
import by.itminsk.cyclingclubbackend.team.TeamService;
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
public class AdminController {

    private final UserService userService;

    @PostMapping("/signup-complete")
    public ResponseEntity<?> register (@ModelAttribute RegisterByAdminDto registerDto, HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + "---" + authorities);
        response.getHeaderNames().forEach(System.out::println);
        return  userService.registerByAdmin(registerDto);
    }

    @PostMapping("/user/edit")
    public ResponseEntity<?> postEditableUser(@ModelAttribute UpdateUserDTO updateUserDTO) {
        return userService.editUserByAdmin(updateUserDTO);

    }
}
