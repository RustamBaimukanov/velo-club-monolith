package by.itminsk.cyclingclubbackend.service.trophy;

import by.itminsk.cyclingclubbackend.model.user.Trophy;
import by.itminsk.cyclingclubbackend.repository.TrophyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrophyServiceImpl implements TrophyService{

    @Autowired
    private TrophyRepository trophyRepository;

    @Override
    public void addTrophy(Trophy trophy) {
        trophyRepository.save(trophy);
    }
}
