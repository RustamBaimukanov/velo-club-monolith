package by.itminsk.cyclingclubbackend.service.team;

import by.itminsk.cyclingclubbackend.model.user.Team;
import by.itminsk.cyclingclubbackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }
}
