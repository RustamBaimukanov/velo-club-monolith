package by.itminsk.cyclingclubbackend.util.security;

import by.itminsk.cyclingclubbackend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository iUserRepository ;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return iUserRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(()-> new UsernameNotFoundException("User not found !"));
    }


}
