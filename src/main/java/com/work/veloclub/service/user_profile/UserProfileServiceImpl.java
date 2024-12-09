package com.work.veloclub.service.user_profile;

import com.work.veloclub.repository.user_profile.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService{

    private final UserProfileRepository userProfileRepository;


    @Override
    public Boolean existsByEmail(String email) {
        return userProfileRepository.existsByEmail(email);
    }
}
