package com.work.veloclub.mapper.team;

import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamDTO;
import com.work.veloclub.model.team.TeamResponse;
import com.work.veloclub.model.team.TeamWithUsersDTO;
import com.work.veloclub.model.user.UserGetDto;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamDTO mapToTeamDto(Team team) {
        if (team == null) {
            return null;
        }
        return new TeamDTO(team.getId(), team.getName(), team.getPhoto() != null ? Base64.getEncoder().encodeToString(team.getPhoto()) : null);
    }

    public static TeamWithUsersDTO mapToTeamWithUsersDto(Team team) {
        if (team == null) {
            return null;
        }
        if (team.getUserProfiles() == null)
            return new TeamWithUsersDTO(team.getId(), team.getName(), team.getPhoto() != null ? Base64.getEncoder().encodeToString(team.getPhoto()) : null, null);
        return new TeamWithUsersDTO(team.getId(), team.getName(), team.getPhoto() != null ? Base64.getEncoder().encodeToString(team.getPhoto()) : null, team.getUserProfiles().stream().map(userProfile ->
                new UserGetDto(
                        userProfile.getId(),
                        userProfile.getEmail(),
                        userProfile.getFirstName(),
                        userProfile.getLastName(),
                        userProfile.getSurname(),
                        userProfile.getPhoto() != null ? Base64.getEncoder().encodeToString(userProfile.getPhoto()) : null,
                        userProfile.getPhotoFormat())
        ).toList());
    }

    public static List<TeamDTO> mapToTeamDtoList(List<Team> teams) {
        if (teams.isEmpty()) {
            return null;
        }

        return teams.stream().map(TeamMapper::mapToTeamDto).collect(Collectors.toList());
    }

    public static List<TeamWithUsersDTO> mapToTeamWithUsersDtoList(List<Team> teams) {
        if (teams.isEmpty()) {
            return null;
        }

        return teams.stream().map(TeamMapper::mapToTeamWithUsersDto).collect(Collectors.toList());
    }

    public static TeamResponse mapToTeamResponse(Team team) {
        return new TeamResponse(team.getId(), team.getName(), team.getPhoto() != null ? Base64.getEncoder().encodeToString(team.getPhoto()) : null);
    }

    public static List<TeamResponse> mapToTeamResponse(List<Team> teams) {
        return teams.stream().map(TeamMapper::mapToTeamResponse).toList();
    }
}
