package by.itminsk.cyclingclubbackend.controller.get;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.city.CityService;
import by.itminsk.cyclingclubbackend.service.role.RoleService;
import by.itminsk.cyclingclubbackend.service.team.TeamService;
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
public class GetController {

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private RoleService roleService;

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
