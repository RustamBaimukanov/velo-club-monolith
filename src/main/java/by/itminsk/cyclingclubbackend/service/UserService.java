package by.itminsk.cyclingclubbackend.service;

import by.itminsk.cyclingclubbackend.model.login.LoginDTO;
import by.itminsk.cyclingclubbackend.model.login.LoginStatus;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;

import java.util.List;

public interface UserService {

    void create(User user);

    List<User> getAll();

    User get(long id);

    boolean update(User user, long id);

    boolean delete(long id);

    void registration(UserDTO userDTO);

    LoginStatus authorize(LoginDTO loginDTO);

}
