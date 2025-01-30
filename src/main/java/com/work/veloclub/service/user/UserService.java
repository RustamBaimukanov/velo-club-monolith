package com.work.veloclub.service.user;

import com.work.veloclub.model.sms.ResetPasswordDto;
import com.work.veloclub.model.sms.ResetPhoneDto;
import com.work.veloclub.model.user.*;
import com.work.veloclub.model.user.dto.UpdateUserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

//    void create(User user);

//    List<User> getAll();

//    User get(long id);

//    boolean update(User user, long id);

//    boolean delete(long id);


//    void createAdmin();

    //LoginStatus authorize(LoginDTO loginDTO);

    /**
     * Авторизует пользователя в системе
     * Если пользователь ввел верные данные, дарует ему access token
     * Если пользователь ввел неверный номер или пароль, инициирует ответ с кодом 401
     * Если пользователь ввел невалидные данные(например кривой номер телефона), инициирует ответ с кодом 400
     * @param loginDto - объект с номером телефона и паролем
     * @return возвращает access токен
     */
    ResponseEntity<BearerToken> authenticate(LoginDto loginDto);

//    ResponseEntity<?> restorePassword(LoginDto loginDto);

    /**
     * Регистрирует пользователя в системе
     * @param registerDto - объект с первичными данными для регистрации велолюбителя.
     * @return возвращает access token
     */
    ResponseEntity<BearerToken> register (RegisterDto registerDto);

    /**
     * Регистрирует пользователя в системе от имени администратора.
     * @param registerDto - объект с данными для регистрации пользователя.
     * @return возвращает ответ с кодом 200
     */
    ResponseEntity<?> register (RegisterByAdminDto registerDto);

    ResponseEntity<?> changePassword (ResetPasswordDto resetPasswordDto);
    ResponseEntity<?> changePassword (UserPasswordDto userPasswordDto);
    ResponseEntity<?> changePhoneNumber (ResetPhoneDto resetPhoneDto);


    //    void registerByAdmin(@Valid User user);
//
//    Boolean confirmPassword (String password, String confirmPassword);
//
//    ResponseEntity<?> changePhoneNumber(String oldPhoneNumber, String newPhoneNumber);
//
//
//    Role saveRole(Role role);
//
//    User saverUser (User user);
//
      Boolean existsByPhoneNumber(String phoneNumber);
//
//    Boolean existByEmail(String email);


//    User getUser(String phoneNumber);

    /**
     *
     * @param updateUserDTO
     */
    void updateMe(UpdateUserDTO updateUserDTO);

    void updateUser(Long id, UpdateUserDTO updateUserDTO);


    User getMe();

    User getUser(Long id);


//    EditUserDTO getEditableUser(String phoneNumber);

//    UserMenuDTO getUserMenu(String phoneNumber);

//    ResponseEntity<?> editUser(UpdateUserDTO updateUserDTO);
//
//    ResponseEntity<?> editUserByAdmin(Long id, UpdateUserDTO updateUserDTO);


//    Long getIdFromPhoneNumber(String phoneNumber);
//
//    UserGetDto getUser(Long id);

//    List<UserGetDto> getUser();

//    List<UserGetDto> getUsersExceptRole(RolesEnum role);

//    void sameUserValidator(Long userId, String phoneNumber);
//
//    void userExistValidator(Long id);
//
//    void userExistValidator(Set<Long> ids);

    void uniqueEmailValidator(Long userId, String email);

    /**
     * Проверка введенного пользователем телефона и почты на уникальность(перед регистрацией)
     * Если проверка не проходит, инициирует ответ с кодом 400
     * @param phoneNumber номер телефона
     * @param email почта
     */
    void uniqueUserValidator(String phoneNumber, String email);


//    void voteFor(Long answerId);
//
//    void cancelVoteFor(Long answerId);

    User findUserById(Long id);


    User findUserByPhoneNumber(String phoneNumber);

    User getUserWithRoleById(Long id);

    void banUser();

    void banUser(Long id);

    void activateUser(Long id);


//    UserInfoDTO getUserProfile(Long id);

}
