package com.work.veloclub.service.team;

import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.team.TeamDTO;

import com.work.veloclub.repository.team.TeamRepository;
import com.work.veloclub.repository.user.UserRepository;
import com.work.veloclub.model.user.UserGetDto;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final UserRepository userRepository;

    @Override
    public TeamDTO getTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Команда не найдена"));
        return TeamDTO.builder().id(team.getId()).name(team.getName()).build();
    }

    @Override
    public List<TeamDTO> getTeam() {
        return teamRepository.findAll().stream().map(team -> new TeamDTO(team.getId(), team.getName())).collect(Collectors.toList());
    }

    @Override
    public List<UserGetDto> getTeamUsers(Long id) {
        return userRepository.findUsersByTeamId(id);
    }

    @Override
    public void teamExistenceValidator(Long id) {
        if (!teamRepository.existsById(id)) throw new ObjectNotFound("Команда не найден.");

    }
}
