package by.itminsk.cyclingclubbackend.service;

import by.itminsk.cyclingclubbackend.model.login.LoginDTO;
import by.itminsk.cyclingclubbackend.model.login.LoginStatus;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import by.itminsk.cyclingclubbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public boolean update(User user, long id) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void registration(UserDTO userDTO) {

        User user = new User(
                userDTO.getId(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepository.save(user);
    }

    @Override
    public LoginStatus authorize(LoginDTO loginDTO) {
        User user = userRepository.findUserByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> existUser = userRepository.findUserByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (existUser.isPresent()) {
                    return new LoginStatus("Login Success", true);
                } else {
                    return new LoginStatus("Login Failed", false);
                }
            } else {

                return new LoginStatus("password Not Match", false);
            }
        } else {
            return new LoginStatus("Email not exits", false);
        }
    }
}
