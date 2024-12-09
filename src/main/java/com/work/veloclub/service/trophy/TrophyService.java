package com.work.veloclub.service.trophy;

import com.work.veloclub.model.trophy.Trophy;
import com.work.veloclub.model.user.User;
import com.work.veloclub.model.user_profile.UserProfile;

import java.util.Set;

public interface TrophyService {

    void addTrophy(Long groupId, Long typeId, UserProfile userProfile, String description);

//    Set<Trophy> findAllByUserId(Long userId);
//
//    Set<Trophy> findAllByPhoneNumber(String phoneNumber);

}
