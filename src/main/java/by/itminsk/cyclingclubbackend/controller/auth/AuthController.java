package by.itminsk.cyclingclubbackend.controller.auth;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.user.City;
import by.itminsk.cyclingclubbackend.model.user.Role;
import by.itminsk.cyclingclubbackend.model.user.Team;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.city.CityService;
import by.itminsk.cyclingclubbackend.service.role.RoleService;
import by.itminsk.cyclingclubbackend.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/signup-complete")
    public ResponseEntity<?> register (@ModelAttribute RegisterByAdminDto registerDto) throws IOException {

        System.out.println(registerDto.getUserImg().getOriginalFilename());
        System.out.println(registerDto.getUserImg().getContentType());

        return  userService.registerByAdmin(registerDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@RequestBody RegisterDto registerDto)
    { return  userService.register(registerDto);}

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto)
    {
        return  userService.authenticate(loginDto);
    }

    @PostMapping("/restore-password")
    public ResponseEntity<?> restorePassword(@RequestBody LoginDto loginDto)
    {
        return  userService.restorePassword(loginDto);
    }


    @GetMapping("/xxx")
    public String main(){
        return "IS Authenticated";
    }

    @GetMapping("/get/cities")
    public List<City> getCities(){
        return cityService.getCities();
    }

    @GetMapping("/get/teams")
    public List<Team> getTeams(){
        return teamService.getTeams();
    }

    @GetMapping("/get/qualifications")
    public List<Role> getQualifications(){
        return roleService.getQualifications();
    }



}
