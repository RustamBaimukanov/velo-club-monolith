package by.itminsk.cyclingclubbackend.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team getTeam(String name) {
        return teamRepository.findTeamByName(name);
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findTeamById(id);
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }
}
