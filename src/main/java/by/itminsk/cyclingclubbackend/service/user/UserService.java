package by.itminsk.cyclingclubbackend.service.user;

import by.itminsk.cyclingclubbackend.model.role.RolesEnum;
import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.model.role.Role;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService {

    void create(User user);

    List<User> getAll();

    User get(long id);

    boolean update(User user, long id);

    boolean delete(long id);

    void registration(UserDTO userDTO);

    void createAdmin();

    //LoginStatus authorize(LoginDTO loginDTO);

    ResponseEntity<?> authenticate(LoginDto loginDto);

    ResponseEntity<?> restorePassword(LoginDto loginDto);


    ResponseEntity<?> register (RegisterDto registerDto);

    ResponseEntity<?> registerByAdmin(RegisterByAdminDto registerByAdminDto) throws IOException;

    Boolean confirmPassword (String password, String confirmPassword);

    ResponseEntity<?> changePhoneNumber(String oldPhoneNumber, String newPhoneNumber);


    Role saveRole(Role role);

    User saverUser (User user);

    Boolean existByPhoneNumber(String phoneNumber);

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

    User findUserByPhoneNumber(String phoneNumber);


    UserInfoDTO getUserProfile(Long id);
}
