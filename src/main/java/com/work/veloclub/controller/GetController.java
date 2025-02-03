package com.work.veloclub.controller;

import com.work.veloclub.mapper.team.TeamMapper;
import com.work.veloclub.model.city.City;
import com.work.veloclub.model.team.TeamDTO;
import com.work.veloclub.model.team.TeamDTODeprecated;
import com.work.veloclub.model.role.RoleDtoDeprecated;
import com.work.veloclub.service.user.UserService;
import com.work.veloclub.service.city.CityService;
import com.work.veloclub.service.role.RoleService;
import com.work.veloclub.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/get")
@RequiredArgsConstructor
/**
 * Контроллер для получения некоторых справочных данных без доступа по токенам(пригодится для фронтенда)
 */
public class GetController {

    private final UserService userService;

    private final CityService cityService;

    private final TeamService teamService;

    private final RoleService roleService;


    @GetMapping("/teams")
    public List<TeamDTO> getTeams(){
        return TeamMapper.mapToTeamDtoList(teamService.getTeam());
    }

    @GetMapping("/qualifications")
    public List<RoleDtoDeprecated> getQualifications(){
        return roleService.getQualifications();
    }

    @GetMapping("/roles")
    public List<RoleDtoDeprecated> getRoles(){
        return roleService.getRoles();
    }

}
