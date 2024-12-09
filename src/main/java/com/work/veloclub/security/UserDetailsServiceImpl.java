package com.work.veloclub.security;

import com.work.veloclub.repository.user.UserRepository;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findUserWithRoleByPhoneNumber(phoneNumber).orElseThrow(()-> new UsernameNotFoundException(ErrorMessages.UserErrors.NOT_FOUND));
    }


}
