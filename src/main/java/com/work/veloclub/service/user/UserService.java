package com.work.veloclub.service.user;

import com.work.veloclub.model.role.RolesEnum;
import com.work.veloclub.model.user.*;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.user.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

    void create(User user);

    List<User> getAll();

    User get(long id);

    boolean update(User user, long id);

    boolean delete(long id);


    void createAdmin();

    //LoginStatus authorize(LoginDTO loginDTO);

    ResponseEntity<?> authenticate(LoginDto loginDto);

    ResponseEntity<?> restorePassword(LoginDto loginDto);


    ResponseEntity<?> register (@Valid User user);

    void registerByAdmin(@Valid User user);

    Boolean confirmPassword (String password, String confirmPassword);

    ResponseEntity<?> changePhoneNumber(String oldPhoneNumber, String newPhoneNumber);


    Role saveRole(Role role);

    User saverUser (User user);

    Boolean existByPhoneNumber(String phoneNumber);

    Boolean existByEmail(String email);


    User getUser(String phoneNumber);

    UserInfoDTO getUserInfo(String phoneNumber);

    EditUserDTO getEditableUser(String phoneNumber);

    UserMenuDTO getUserMenu(String phoneNumber);

    ResponseEntity<?> editUser(UpdateUserDTO updateUserDTO);

    ResponseEntity<?> editUserByAdmin(Long id, UpdateUserDTO updateUserDTO);


    Long getIdFromPhoneNumber(String phoneNumber);

    UserGetDto getUser(Long id);

    List<UserGetDto> getUser();

    List<UserGetDto> getUsersExceptRole(RolesEnum role);

    void sameUserValidator(Long userId, String phoneNumber);

    void userExistValidator(Long id);

    void userExistValidator(Set<Long> ids);

    void uniqueUserValidator(String phoneNumber, String email);

    void voteFor(Long answerId);

    void cancelVoteFor(Long answerId);


    User findUserByPhoneNumber(String phoneNumber);


    UserInfoDTO getUserProfile(Long id);

}
