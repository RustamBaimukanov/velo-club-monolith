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

    /**
     * Смена пароля без проверки старого пароля
     * @param resetPasswordDto
     * @return возвращает ответ с кодом 200
     */
    ResponseEntity<?> changePassword (ResetPasswordDto resetPasswordDto);

    /**
     * Смена пароля с проверкой старого пароля
     * @param userPasswordDto
     * @return возвращает ответ с кодом 200
     */
    ResponseEntity<?> changePassword (UserPasswordDto userPasswordDto);

    /**
     * Пользователь меняет номер телефона
     * @param resetPhoneDto
     * @return возвращает новый токен
     * @warning когда меняется номер телефона, клиенту необходимо поменять старый токен на тот что возвращает этот метод
     */
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
     * Редактирует данные профиля пользователя
     * @see com.work.veloclub.repository.user.UserRepository#findUserWithProfileAndSocialNetworksByPhoneNumber
     * @param updateUserDTO - объект с данными о профиле пользователе
     * @warning внутри метода отсутствуют проверки, но они есть во внешнем слое(валидация, проверка id регионов и команд. Потенциально возможны ошибки 500)
     * @warning что если пользователь решит поменять команду во время мероприятия, или после командного соревнования?
     * @bug сохранение соцсетей
     */
    void updateMe(UpdateUserDTO updateUserDTO);

    /**
     * Админ редактирует данные профиля пользователя по id
     * @see com.work.veloclub.repository.user.UserRepository#findUserWithProfileAndSocialNetworksById
     * @param id - id пользователя
     * @param updateUserDTO - объект с данными о профиле пользователе
     * @warning внутри метода отсутствуют проверки, но они есть во внешнем слое(валидация, проверка id регионов и команд. Потенциально возможны ошибки 500)
     * @warning что если админ решит поменять команду пользователю во время мероприятия, или после командного соревнования?
     * @bug сохранение соцсетей
     */
    void updateUser(Long id, UpdateUserDTO updateUserDTO);

    /**
     * По токену отдает информацию о пользователе
     * @see com.work.veloclub.repository.user.UserRepository#findUserWithProfileByPhoneNumber
     * @return возвращает пользователя вместе с профилем по токену
     */
    User getMe();

    /**
     * По id отдает информацию о пользователе
     * @see com.work.veloclub.repository.user.UserRepository#findUserWithProfileById
     * @return возвращает пользователя вместе с профилем по токену
     */
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

    /**
     * Отдает User по номеру телефона
     * Если пользователь не найден инициирует ответ с кодом 400
     * @param phoneNumber - номер телефона
     * @return возвращает объект User
     */
    User findUserByPhoneNumber(String phoneNumber);

    User getUserWithRoleById(Long id);

    /**
     * Блокирует пользователя по токену(имитация функционала удаления профиля)
     */
    void banUser();

    /**
     * Блокирует пользователя по id
     * @param id - id пользователя
     */
    void banUser(Long id);

    /**
     * Активирует пользователя по id
     * @param id - id пользователя
     */
    void activateUser(Long id);


//    UserInfoDTO getUserProfile(Long id);

}
