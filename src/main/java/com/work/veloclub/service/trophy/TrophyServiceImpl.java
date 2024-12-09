package com.work.veloclub.service.trophy;

import com.work.veloclub.constants.TrophyConstants;
import com.work.veloclub.model.trophy.Trophy;

import com.work.veloclub.model.trophy_group.TrophyGroup;
import com.work.veloclub.model.trophy_type.TrophyType;
import com.work.veloclub.model.user_profile.UserProfile;
import com.work.veloclub.repository.trophy.TrophyRepository;
import com.work.veloclub.repository.trophy_group.TrophyGroupRepository;
import com.work.veloclub.repository.trophy_type.TrophyTypeRepository;
import com.work.veloclub.repository.user.UserRepository;
import com.work.veloclub.model.user.User;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
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
    public void addTrophy(Long groupId, Long typeId, UserProfile userProfile, String description) {
        TrophyGroup trophyGroup = trophyGroupRepository.findById(TrophyConstants.TrophyGroupIds.REGISTRATION).orElseThrow(() -> new ObjectNotFound("Причина выдачи ачивки не найдена."));
        TrophyType trophyType = trophyTypeRepository.findById(TrophyConstants.TrophyTypeIds.GOLD_CUP).orElseThrow(() -> new ObjectNotFound("Ачивка не найдена."));
        Trophy trophy = new Trophy();
        trophy.setTrophyType(trophyType);
        trophy.setTrophyGroup(trophyGroup);
        trophy.setDescription(description);
        trophy.setUserProfile(userProfile);
        trophyRepository.save(trophy);
    }

//    @Override
//    public Set<Trophy> findAllByUserId(Long userId) {
//        return trophyRepository.findAllByUserId(userId);
//    }
//
//    @Override
//    public Set<Trophy> findAllByPhoneNumber(String phoneNumber) {
//        return findAllByUserId(userRepository.getIdFromPhoneNumber(phoneNumber));
//    }
}
