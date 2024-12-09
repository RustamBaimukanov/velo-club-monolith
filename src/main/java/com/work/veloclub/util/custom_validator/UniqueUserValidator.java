package com.work.veloclub.util.custom_validator;

import com.work.veloclub.repository.user.UserRepository;
import com.work.veloclub.repository.user_profile.UserProfileRepository;
import com.work.veloclub.util.exception_handler.UniqueObjectExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueUserValidator {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public void validate(Long userId, String email) {
        boolean emailExists = email != null && userProfileRepository.existsByEmailAndNotId(userId, email);

        if (emailExists) {
            throw new UniqueObjectExistException("Пользователь с данным email уже зарегистрирован!");
        }
    }

    public void validate(String phoneNumber, String email) {
        boolean phoneExists = userRepository.existsByPhoneNumber(phoneNumber);
        boolean emailExists = email != null && userProfileRepository.existsByEmail(email);

        if (phoneExists && emailExists) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном и почтой уже зарегистрирован!");
        } else if (emailExists) {
            throw new UniqueObjectExistException("Пользователь с данным email уже зарегистрирован!");
        } else if (phoneExists) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном уже зарегистрирован!");
        }
    }


}

