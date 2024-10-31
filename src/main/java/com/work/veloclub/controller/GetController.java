package com.work.veloclub.controller;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.team.TeamDTO;
import com.work.veloclub.model.user.LoginDto;
import com.work.veloclub.model.role.RoleDto;
import com.work.veloclub.model.user.User;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.service.city.CityService;
import com.work.veloclub.service.role.RoleService;
import com.work.veloclub.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<TeamDTO> getTeams(){
        return teamService.getTeam();
    }

    @GetMapping("/qualifications")
    public List<RoleDto> getQualifications(){
        return roleService.getQualifications();
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles(){
        return roleService.getRoles();
    }


    @GetMapping("/user-img")
    public ResponseEntity<byte[]> getFile(@RequestBody LoginDto loginDto) {

        User user = userService.getUser(loginDto.getPhoneNumber());
        String filename = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(user.getPhotoFormat()));
        headers.add("content-disposition", "inline;filename=" + filename);
        return new ResponseEntity<byte[]>(user.getPhoto(), headers, HttpStatus.OK);
    }
}
