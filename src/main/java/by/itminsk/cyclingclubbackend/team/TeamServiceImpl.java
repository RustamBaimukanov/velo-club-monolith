package by.itminsk.cyclingclubbackend.team;

import by.itminsk.cyclingclubbackend.team.dto.TeamDTO;

import by.itminsk.cyclingclubbackend.user.UserRepository;
import by.itminsk.cyclingclubbackend.user.dto.UserGetDto;
import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{

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
}
