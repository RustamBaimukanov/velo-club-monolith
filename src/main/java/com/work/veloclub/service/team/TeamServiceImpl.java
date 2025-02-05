package com.work.veloclub.service.team;

import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamCreateRequest;
import com.work.veloclub.model.team.TeamUpdateRequest;
import com.work.veloclub.model.user.UserGetDto;
import com.work.veloclub.repository.team.TeamRepository;
import com.work.veloclub.repository.user_profile.UserProfileRepository;
import com.work.veloclub.util.CustomMultipartFile;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final UserProfileRepository userProfileRepository;

    @Override
    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Команда не найдена"));
    }

    @Override
    public List<Team> getTeam() {
        return teamRepository.findAll();
    }

    @Override
    public List<UserGetDto> getTeamUsers(Long id) {
        return userProfileRepository.findAllByTeamId(id).stream()
                .map(userProfile ->
                        new UserGetDto(
                                userProfile.getId(),
                                userProfile.getEmail(),
                                userProfile.getFirstName(),
                                userProfile.getLastName(),
                                userProfile.getSurname(),
                                userProfile.getPhoto() != null ? Base64.getEncoder().encodeToString(userProfile.getPhoto()) : null,
                                userProfile.getPhotoFormat())
                ).toList();
    }

    @Override
    public List<Team> getTeamWithUsers() {
        return teamRepository.findAllWithUsers();
    }

    @Override
    public void teamExistenceValidator(Long id) {
        if (id != null && !teamRepository.existsById(id)) throw new ObjectNotFound(ErrorMessages.TeamErrors.NOT_FOUND);

    }

    @Override
    @Transactional
    public Team createTeam(TeamCreateRequest teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.name());
        team.setPhoto(teamDTO.photo() != null ? Base64.getDecoder().decode(teamDTO.photo()) : null);
        teamRepository.save(team);
        return team;
    }

    @Override
    @Transactional
    public Team update(Long id, TeamUpdateRequest teamDTO) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.TeamErrors.NOT_FOUND));
        team.setName(teamDTO.name());
        team.setPhoto(teamDTO.photo() != null ? Base64.getDecoder().decode(teamDTO.photo()) : null);
        return team;
    }

    @Override
    @Transactional
    public List<Team> createTeamsGenerate(List<TeamCreateRequest> teamsDTO) {
        List<Team> teams = teamsDTO.stream().map(teamCreateRequest -> {
            Team team = new Team();
            team.setName(teamCreateRequest.name());
            return team;
        }).toList();
        teamRepository.saveAll(teams);
        return teams;
    }
}
