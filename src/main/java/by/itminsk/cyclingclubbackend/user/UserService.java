package by.itminsk.cyclingclubbackend.user;

import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import by.itminsk.cyclingclubbackend.user.dto.LoginDto;
import by.itminsk.cyclingclubbackend.user.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.user.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.util.exception_handler.RestoreUserNotFound;
import by.itminsk.cyclingclubbackend.role.Role;
import by.itminsk.cyclingclubbackend.user.dto.*;
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

    User registerAuto (RegisterDto registerDto);


    ResponseEntity<?> registerByAdmin(RegisterByAdminDto registerByAdminDto) throws IOException;

    Boolean confirmPassword (String password, String confirmPassword);

    ResponseEntity<?> changePhoneNumber(String oldPhoneNumber, String newPhoneNumber);


    Role saveRole(Role role);

    User saverUser (User user);

    Boolean existByPhoneNumber(String phoneNumber);

    ResponseEntity<?> existByPhoneNumberAndEmail(String phoneNumber, String email);

    User getUser(String phoneNumber);

    UserInfoDTO getUserInfo(String phoneNumber);

    EditUserDTO getEditableUser(String phoneNumber);

    UserMenuDTO getUserMenu(String phoneNumber);

    ResponseEntity<?> editUser(UpdateUserDTO updateUserDTO);

    ResponseEntity<?> editUserByAdmin(UpdateUserDTO updateUserDTO);


    Long getIdFromPhoneNumber(String phoneNumber);

    List<UserGetDto> getUsersExceptRole(RoleEnum role);

    void userExistValidator(Long id);

    void userExistValidator(Set<Long> ids);


}
