package by.itminsk.cyclingclubbackend.service.trophy;

import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.repository.TrophyGroupRepository;
import by.itminsk.cyclingclubbackend.repository.TrophyRepository;
import by.itminsk.cyclingclubbackend.repository.TrophyTypeRepository;
import by.itminsk.cyclingclubbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TrophyServiceImpl implements TrophyService {

    @Autowired
    private TrophyRepository trophyRepository;

    @Autowired
    private TrophyGroupRepository trophyGroupRepository;

    @Autowired
    private TrophyTypeRepository trophyTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addTrophy(Long groupId, Long typeId, User user, String description) {
        TrophyGroup trophyGroup = trophyGroupRepository.getById(1L);
        TrophyType trophyType = trophyTypeRepository.getById(6L);
        Trophy trophy = new Trophy();
        trophy.setTrophyType(trophyType);
        trophy.setTrophyGroup(trophyGroup);
        trophy.setDescription(description);
        trophy.setUser(user);
        trophyRepository.save(trophy);
    }

    @Override
    public Set<Trophy> findAllByUserId(Long userId) {
        return trophyRepository.findAllByUserId(userId);
    }

    @Override
    public Set<Trophy> findAllByPhoneNumber(String phoneNumber) {
        return findAllByUserId(userRepository.getIdFromPhoneNumber(phoneNumber));
    }
}
