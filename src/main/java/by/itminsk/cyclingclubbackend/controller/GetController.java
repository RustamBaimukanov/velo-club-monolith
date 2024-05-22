package by.itminsk.cyclingclubbackend.controller;

import by.itminsk.cyclingclubbackend.r_city.City;
import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import by.itminsk.cyclingclubbackend.user.dto.LoginDto;
import by.itminsk.cyclingclubbackend.role.dto.RoleDto;
import by.itminsk.cyclingclubbackend.team.Team;
import by.itminsk.cyclingclubbackend.user.User;
import by.itminsk.cyclingclubbackend.user.UserService;
import by.itminsk.cyclingclubbackend.r_city.CityService;
import by.itminsk.cyclingclubbackend.role.RoleService;
import by.itminsk.cyclingclubbackend.team.TeamService;
import by.itminsk.cyclingclubbackend.user.dto.UserGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/get")
@RequiredArgsConstructor
public class GetController {

    private final UserService userService;

    private final CityService cityService;

    private final TeamService teamService;

    private final RoleService roleService;

    @GetMapping("/cities")
    public List<City> getCities(){
        return cityService.getCities();
    }

    @GetMapping("/teams")
    public List<Team> getTeams(){
        return teamService.getTeams();
    }

    @GetMapping("/qualifications")
    public List<RoleDto> getQualifications(){
        return roleService.getQualifications();
    }

    @GetMapping("/users-except-role")
    public List<UserGetDto> getUsersExceptRole(@RequestParam RoleEnum role){
        return userService.getUsersExceptRole(role);
    }

    @GetMapping("/user-img")
    public ResponseEntity<byte[]> getFile(@RequestBody LoginDto loginDto) {

        User user = userService.getUser(loginDto.getTel());
        String filename = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(user.getPhotoFormat()));
        headers.add("content-disposition", "inline;filename=" + filename);
        return new ResponseEntity<byte[]>(user.getPhoto(), headers, HttpStatus.OK);
    }
}
