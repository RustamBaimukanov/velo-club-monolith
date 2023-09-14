package by.itminsk.cyclingclubbackend.controller.admin;

import by.itminsk.cyclingclubbackend.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.city.CityService;
import by.itminsk.cyclingclubbackend.service.role.RoleService;
import by.itminsk.cyclingclubbackend.service.team.TeamService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("api/private")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private RoleService roleService;


    @PostMapping("/signup-complete")
    public ResponseEntity<?> register (@ModelAttribute RegisterByAdminDto registerDto, HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        System.out.println(currentPrincipalName + "---" + authorities);
        response.getHeaderNames().forEach(System.out::println);
        return  userService.registerByAdmin(registerDto);
    }
}
