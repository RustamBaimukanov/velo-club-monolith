package com.work.veloclub.mapper.team;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.city.CityDTO;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamDTO;
import com.work.veloclub.model.team.TeamResponse;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamDTO mapToTeamDto(Team team){
        if (team == null){
            return null;
        }

        return new TeamDTO(team.getId(), team.getName());
    }

    public static List<TeamDTO> mapToTeamDtoList(List<Team> teams){
        if (teams.isEmpty()){
            return null;
        }

        return teams.stream().map(TeamMapper::mapToTeamDto).collect(Collectors.toList());
    }

    public static TeamResponse mapToTeamResponse(Team team) {
        return new TeamResponse(team.getId(), team.getName(), team.getPhoto(), team.getPhotoFormat());
    }

    public static List<TeamResponse> mapToTeamResponse(List<Team> teams) {
        return teams.stream().map(TeamMapper::mapToTeamResponse).toList();
    }
}
