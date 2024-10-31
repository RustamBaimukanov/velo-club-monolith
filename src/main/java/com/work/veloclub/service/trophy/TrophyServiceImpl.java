package com.work.veloclub.service.trophy;

import com.work.veloclub.model.trophy.Trophy;

import com.work.veloclub.model.trophy_group.TrophyGroup;
import com.work.veloclub.model.trophy_type.TrophyType;
import com.work.veloclub.repository.trophy.TrophyRepository;
import com.work.veloclub.repository.trophy_group.TrophyGroupRepository;
import com.work.veloclub.repository.trophy_type.TrophyTypeRepository;
import com.work.veloclub.repository.user.UserRepository;
import com.work.veloclub.model.user.User;
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
