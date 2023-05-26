package by.itminsk.cyclingclubbackend.service;

import by.itminsk.cyclingclubbackend.model.User;

import java.util.List;

public interface UserService {

    void create(User user);

    List<User> getAll();

    User get(long id);

    boolean update(User user, long id);

    boolean delete(long id);
}
