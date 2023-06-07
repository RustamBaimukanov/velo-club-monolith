package by.itminsk.cyclingclubbackend.service;

import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.login.LoginStatus;
import by.itminsk.cyclingclubbackend.model.user.Role;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    void create(User user);

    List<User> getAll();

    User get(long id);

    boolean update(User user, long id);

    boolean delete(long id);

    void registration(UserDTO userDTO);

    void createAdmin();

    //LoginStatus authorize(LoginDTO loginDTO);

    String authenticate(LoginDto loginDto);
    ResponseEntity<?> register (RegisterDto registerDto);

    Boolean confirmPassword (String password, String confirmPassword);


    Role saveRole(Role role);

    User saverUser (User user) ;



}
