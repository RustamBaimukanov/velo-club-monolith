package com.work.veloclub.service.user;

import com.work.veloclub.constants.RoleConstants;
import com.work.veloclub.mapper.user.UserMapper;
import com.work.veloclub.model.city.City;
import com.work.veloclub.model.role.Role;
import com.work.veloclub.model.role.RoleEnum;
import com.work.veloclub.model.sms.ResetPasswordDto;
import com.work.veloclub.model.sms.ResetPhoneDto;
import com.work.veloclub.model.social_network.SocialNetwork;
import com.work.veloclub.model.team.Team;
import com.work.veloclub.model.user.*;
import com.work.veloclub.model.user.dto.UpdateUserDTO;
import com.work.veloclub.model.user_profile.UserProfile;
import com.work.veloclub.repository.answer.AnswerRepository;
import com.work.veloclub.repository.role.RoleRepository;
import com.work.veloclub.repository.social_network.SocialNetworkRepository;
import com.work.veloclub.repository.user.UserRepository;
import com.work.veloclub.repository.user_profile.UserProfileRepository;
import com.work.veloclub.security.JwtUtilities;
import com.work.veloclub.service.city.CityService;
import com.work.veloclub.service.team.TeamService;
import com.work.veloclub.service.trophy.TrophyService;
import com.work.veloclub.util.custom_validator.UniqueUserValidator;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.PermissionException;
import com.work.veloclub.util.exception_handler.UnacceptableDataException;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserProfileRepository userProfileRepository;

    private final AnswerRepository answerRepository;

    private final TrophyService trophyService;

//    private final SocialNetworkService socialNetworkService;

    private final CityService cityService;

    private final TeamService teamService;


    private final SocialNetworkRepository socialNetworkRepository;

    private final UniqueUserValidator uniqueUserValidator;


    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;

    // Только для отладки
    private final EntityManager entityManager;


//    @Override
//    public void createAdmin() {
//        User user = new User();
//        user.setEmail("Ruler");
//        //user.setPassword(this.passwordEncoder.encode("1111"));
//        user.setRole(roleRepository.findRoleByName(RoleEnum.ADMIN));
//        userRepository.save(user);
//    }


//    @Override
//    public UserInfoDTO getMe(String phoneNumber) {
//        UserInfoDTO userInfoDTO = iUserRepository.getUserByPhoneNumber(phoneNumber);
//        userInfoDTO.setSocialNetworks(socialNetworkRepository.findAllByUserId(userInfoDTO.getId()));
//        userInfoDTO.setTrophies(trophyService.findAllByUserId(userInfoDTO.getId()));
//        userInfoDTO.setEventResults(eventResultsRepository.findAllByUserId(userInfoDTO.getId()));
//        userInfoDTO.setQualification(roleRepository.findRoleByUser(userInfoDTO.getId()));
//        Map<Integer, List<EventResult>> eventMap = userInfoDTO
//                .getEventResults()
//                .stream()
//                .peek(eventResult -> {
//                    eventResult.setDate(eventResult.getEvent().getDate());
//                    eventResult.setName(eventResult.getEvent().getName());
//                })
//                .collect(Collectors.groupingBy(eventResult -> {
//                            Calendar calendar = Calendar.getInstance();
//                            calendar.setTime(eventResult.getEvent().getDate());
//                            return calendar.get(Calendar.YEAR);
//                        }, LinkedHashMap::new, Collectors.toList()
//                ));
//        Map<Integer, List<EventResult>> integerListMap = new LinkedHashMap<>();
//        List<Integer> integerList = eventMap.keySet().stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).toList();
//        for (Integer year :
//                integerList) {
//            integerListMap.put(year, eventMap.get(year));
//        }
//        userInfoDTO.setEvent(integerListMap);
//        return userInfoDTO;
//    }

//    @Override
//    public EditUserDTO getEditableUser(String phoneNumber) {
//        EditUserDTO editUserDTO = iUserRepository.findByPhoneNumber(phoneNumber);
//        editUserDTO.setSocialNetworks(socialNetworkRepository.findAllByUserId(editUserDTO.getId()));
//        editUserDTO.setQualification(userRepository.findRoleByUserId(editUserDTO.getId()));
//        return editUserDTO;
//    }

//    @Override
//    public UserMenuDTO getUserMenu(String phoneNumber) {
//        return iUserRepository.getUserMenuByPhoneNumber(phoneNumber);
//    }

//    @Override
//    public ResponseEntity<?> editUser(UpdateUserDTO updateUserDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        Optional<User> user = iUserRepository.findUserByPhoneNumber(currentPrincipalName);
//        if (updateUserDTO.getEmail() != null) {
//            if (updateUserDTO.getEmail().trim().equals("")) updateUserDTO.setEmail(null);
//        }
//        if (iUserRepository.existsByEmailAndEmailIsNotNull(updateUserDTO.getEmail())
//                && !updateUserDTO.getEmail().equals(user.get().getEmail())) {
//            throw new UniqueObjectExistException("Пользователь с данным email уже существует!");
//        }
////        iUserRepository.findUserByEmailAndEmailIsNotNull(updateUserDTO.getEmail()).if
//        user.ifPresent(u -> {
//            u.setFirstName(updateUserDTO.getFirstName());
//            u.setLastName(updateUserDTO.getLastName());
//            u.setEmail(updateUserDTO.getEmail());
//            u.setBirthDate(updateUserDTO.getBirth());
//            u.setSex(updateUserDTO.getGender());
//            u.setHeight(updateUserDTO.getHeight());
//            u.setWeight(updateUserDTO.getWeight());
//            u.setCity(City.builder().id(updateUserDTO.getRegion()).build());
////            u.setTeam(Team.builder().id(updateUserDTO.getClub()).build());
//            if (u.getRole().getName() == RoleEnum.ADMIN && updateUserDTO.getQualification() != RoleEnum.ADMIN && Objects.equals(currentPrincipalName, u.getPhoneNumber()))
//                throw new UnacceptableDataException("Администратор не может лишить себя прав администратора.");
//            switch (updateUserDTO.getImageStatus()) {
//                case CHANGE_IMG -> {
//                    try {
//                        u.setPhoto(ImageUtil.compressAndEncodeImage(updateUserDTO.getUserImg()));
//                        u.setPhotoFormat(updateUserDTO.getUserImg().getContentType());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                case DELETE_IMG -> {
//                    u.setPhoto(null);
//                    u.setPhotoFormat(null);
//                }
//            }
//            socialNetworkService.saveSocialNetworksWhenUserEdit(u, updateUserDTO.getSocialNetworks());
//            iUserRepository.save(u);
//        });
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity<?> editUserByAdmin(Long id, UpdateUserDTO updateUserDTO) {
//        log.info("Email is : {}", updateUserDTO.getEmail());
//        if (updateUserDTO.getEmail() != null) {
//            updateUserDTO.setEmail(updateUserDTO.getEmail().trim().equals("") ? null : updateUserDTO.getEmail());
//        }
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        Optional<User> user = iUserRepository.findById(id);
//        if (iUserRepository.existsByEmailAndEmailIsNotNull(updateUserDTO.getEmail())
//                && !updateUserDTO.getEmail().equals(user.get().getEmail())) {
//            throw new UniqueObjectExistException("Пользователь с данным email уже существует!");
//        }
//
//
//        user.ifPresent(u -> {
//            u.setFirstName(updateUserDTO.getFirstName());
//            u.setLastName(updateUserDTO.getLastName());
//            u.setEmail(updateUserDTO.getEmail());
//            u.setBirthDate(updateUserDTO.getBirth());
//            u.setSex(updateUserDTO.getGender());
//            u.setHeight(updateUserDTO.getHeight());
//            u.setWeight(updateUserDTO.getWeight());
//            u.setCity(City.builder().id(updateUserDTO.getRegion()).build());
//            if (updateUserDTO.getClub() != null)
//                u.setTeam(Team.builder().id(updateUserDTO.getClub()).build());
//            if (u.getRole().getName() == RoleEnum.ADMIN && updateUserDTO.getQualification() != RoleEnum.ADMIN && Objects.equals(currentPrincipalName, u.getPhoneNumber()))
//                throw new UnacceptableDataException("Администратор не может лишить себя прав администратора.");
//            switch (updateUserDTO.getImageStatus()) {
//                case CHANGE_IMG -> {
//                    try {
//                        u.setPhoto(ImageUtil.compressAndEncodeImage(updateUserDTO.getUserImg()));
//                        u.setPhotoFormat(updateUserDTO.getUserImg().getContentType());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                case NO_CHANGE_IMG -> {
//
//                }
//                case DELETE_IMG -> {
//                    u.setPhoto(null);
//                    u.setPhotoFormat(null);
//                }
//            }
//            socialNetworkService.saveSocialNetworksWhenUserEdit(u, updateUserDTO.getSocialNetworks());
//            iUserRepository.save(u);
//        });
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @Override
//    public List<UserGetDto> getUser() {
//        return null;
//    }

//    @Override
//    public Long getIdFromPhoneNumber(String phoneNumber) {
//        return iUserRepository.getIdFromPhoneNumber(phoneNumber);
//    }

//    @Override
//    public UserGetDto getUser(Long id) {
//        return userRepository.findUserDtoById(id).orElseThrow(() -> new ObjectNotFound("Пользователь не найден."));
//    }

//    @Override
//    public List<UserGetDto> getUser() {
//        return userRepository.findAllUserDto();
//    }

//    @Override
//    public List<UserGetDto> getUsersExceptRole(RolesEnum role) {
//        switch (role) {
//            default -> {
//                return new ArrayList<>();
//            }
//            case SPORTSMAN -> {
//                return userRepository.findAllByRoleName(RoleEnum.DABBLER);
//            }
//            case DABBLER -> {
//                return userRepository.findAllByRoleName(RoleEnum.SPORTSMAN);
//            }
//        }
//    }

//    @Override
//    public void sameUserValidator(Long userId, String phoneNumber) {
//        User user = findUserByPhoneNumber(phoneNumber);
//        if (!(user.getRole().getName() == RoleEnum.ADMIN) && !user.getId().equals(userId)) {
//            throw new PermissionException("Данный пользователь не имеет соответствующих прав доступа.");
//        }
//    }
//
//    @Override
//    public void userExistValidator(Long id) {
//        if (!userRepository.existsById(id)) throw new ObjectNotFound("Пользователь не найден.");
//    }
//
//    @Override
//    public void userExistValidator(Set<Long> ids) {
//        if (!userRepository.existsByIdIn(ids)) throw new ObjectNotFound("Пользователи не найдены.");
//
//    }

    @Override
    public void uniqueEmailValidator(Long userId, String email) {
        uniqueUserValidator.validate(userId, email);
    }


    @Override
    public void uniqueUserValidator(String phoneNumber, String email) {
        uniqueUserValidator.validate(phoneNumber, email);
    }


//    @Override
//    @Transactional
//    public void voteFor(Long answerId) {
//        Answer answer = answerRepository.findById(answerId).orElseThrow(()-> new ObjectNotFound("Ответ не найден."));
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        User user = iUserRepository.findUserByPhoneNumber(currentPrincipalName).orElseThrow(() -> new ObjectNotFound("Не найден."));
//        user.addAnswer(answer);
//        //iUserRepository.save(user);
//    }
//
//    @Override
//    @Transactional
//    public void cancelVoteFor(Long answerId) {
//        Answer answer = answerRepository.findById(answerId).orElseThrow(()-> new ObjectNotFound("Ответ не найден."));
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        User user = iUserRepository.findUserByPhoneNumber(currentPrincipalName).orElseThrow(() -> new ObjectNotFound("Не найден."));
//        user.removeAnswer(answer);
//        //iUserRepository.save(user);
//    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
    }

    @Override
    public User getUserWithRoleById(Long id) {
        return userRepository.findUserWithRoleById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
    }

    @Override
    @Transactional
    public void banUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByPhoneNumber(authentication.getName()).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
        user.setStatus(UserStatusEnum.BLOCKED);

    }

    @Override
    @Transactional
    public void banUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Пользователь не найден.")).setStatus(UserStatusEnum.BLOCKED);
    }

    @Override
    @Transactional
    public void activateUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Пользователь не найден.")).setStatus(UserStatusEnum.ACTIVE);
    }

//    @Override
//    public UserInfoDTO getUserProfile(Long id) {
//        UserInfoDTO userInfoDTO = iUserRepository.getUserById(id);
//        userInfoDTO.setSocialNetworks(socialNetworkRepository.findAllByUserId(userInfoDTO.getId()));
//        userInfoDTO.setTrophies(trophyService.findAllByUserId(userInfoDTO.getId()));
//        userInfoDTO.setEventResults(eventResultsRepository.findAllByUserId(userInfoDTO.getId()));
//        userInfoDTO.setQualification(roleRepository.findRoleByUser(userInfoDTO.getId()));
//        Map<Integer, List<EventResult>> eventMap = userInfoDTO
//                .getEventResults()
//                .stream()
//                .peek(eventResult -> {
//                    eventResult.setDate(eventResult.getEvent().getDate());
//                    eventResult.setName(eventResult.getEvent().getName());
//                })
//                .collect(Collectors.groupingBy(eventResult -> {
//                            Calendar calendar = Calendar.getInstance();
//                            calendar.setTime(eventResult.getEvent().getDate());
//                            return calendar.get(Calendar.YEAR);
//                        }, LinkedHashMap::new, Collectors.toList()
//                ));
//        Map<Integer, List<EventResult>> integerListMap = new LinkedHashMap<>();
//        List<Integer> integerList = eventMap.keySet().stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).toList();
//        for (Integer year :
//                integerList) {
//            integerListMap.put(year, eventMap.get(year));
//        }
//        userInfoDTO.setEvent(integerListMap);
//        return userInfoDTO;
//    }

    @Override
    @Transactional
    public ResponseEntity<BearerToken> register(RegisterDto registerDto) {
        if (registerDto.gender() == GenderEnum.ANY) throw new UnacceptableDataException("");
        User user = UserMapper.mapToUser(registerDto);
        UserProfile userProfile = UserMapper.mapToUserProfile(registerDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.builder().id(RoleConstants.DABBLER).build());
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        userRepository.save(user);
        trophyService.addTrophy(1L, 6L, userProfile, "Выдано за регистрацию");
        String token = jwtUtilities.generateToken(user.getPhoneNumber(), Collections.singletonList(RoleEnum.DABBLER.name()));
        return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> register(RegisterByAdminDto registerDto) {
        if (registerDto.gender() == GenderEnum.ANY) throw new UnacceptableDataException("");
        User user = UserMapper.mapToUser(registerDto);
        UserProfile userProfile = UserMapper.mapToUserProfile(registerDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        switch (registerDto.role()) {
            case ADMIN -> user.setRole(Role.builder().id(RoleConstants.ADMIN).build());
            case SPORTSMAN -> user.setRole(Role.builder().id(RoleConstants.SPORTSMAN).build());
            case DABBLER -> user.setRole(Role.builder().id(RoleConstants.DABBLER).build());
        }
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        userRepository.save(user);
        trophyService.addTrophy(1L, 6L, userProfile, "Выдано за регистрацию");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> changePassword(ResetPasswordDto resetPasswordDto) {
        User user = userRepository.findUserByPhoneNumber(resetPasswordDto.phoneNumber()).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
        user.setPassword(passwordEncoder.encode(resetPasswordDto.newPassword()));
        return ResponseEntity.ok("Пароль изменен");
    }

    @Override
    @Transactional
    public ResponseEntity<?> changePassword(UserPasswordDto userPasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByPhoneNumber(authentication.getName()).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
        if (passwordEncoder.matches(userPasswordDto.oldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userPasswordDto.newPassword()));
        } else return ResponseEntity.ok("Пароль не изменен");
        return ResponseEntity.ok("Пароль изменен");
    }

    @Override
    @Transactional
    public ResponseEntity<?> changePhoneNumber(ResetPhoneDto resetPhoneDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserWithRoleByPhoneNumber(authentication.getName()).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
        user.setPhoneNumber(resetPhoneDto.phoneNumber());

        String token = jwtUtilities.generateToken(user.getPhoneNumber(), Collections.singletonList(user.getRole().getName().name()));
        return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
    }

//    @Override
//    public void registerByAdmin(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        iUserRepository.saveAndFlush(user);
//        trophyService.addTrophy(1L, 6L, user, "Выдано за регистрацию");
//    }

//    @Override
//    public Boolean confirmPassword(String password, String confirmPassword) {
//        return (Objects.equals(password, confirmPassword));
//    }

//    @Override
//    public ResponseEntity<?> changePhoneNumber(String oldPhoneNumber, String newPhoneNumber) throws UsernameNotFoundException {
//        User user = userRepository.findUserByPhoneNumber(oldPhoneNumber).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
//        user.setPhoneNumber(newPhoneNumber);
//        userRepository.save(user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @Override
    public ResponseEntity<BearerToken> authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.phoneNumber(),
                        loginDto.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findUserWithRoleByPhoneNumber(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Некорректный номер телефона или пароль."));
        if (user.getStatus().equals(UserStatusEnum.BLOCKED)) throw new PermissionException("Доступ запрещен");
        return new ResponseEntity<>(new BearerToken(jwtUtilities.generateToken(user.getUsername(), Collections.singletonList(user.getRole().getName().name())), "Bearer "), HttpStatus.OK);
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    @Transactional
    public void updateMe(UpdateUserDTO updateUserDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserWithProfileAndSocialNetworksByPhoneNumber(authentication.getName()).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
        log.info("Сущность:::{}", entityManager.contains(user));
        UserProfile userProfile = user.getUserProfile();
        uniqueEmailValidator(userProfile.getId(), updateUserDTO.email());
        userProfile.setEmail(updateUserDTO.email());
        userProfile.setGender(updateUserDTO.gender());
        userProfile.setBirthDate(updateUserDTO.birthDate());
        userProfile.setFirstName(updateUserDTO.firstName());
        userProfile.setLastName(updateUserDTO.lastName());
        userProfile.setSurname(updateUserDTO.surname());
        if (updateUserDTO.cityId() != null) userProfile.setCity(City.builder().id(updateUserDTO.cityId()).build());
        if (updateUserDTO.teamId() != null) userProfile.setTeam(Team.builder().id(updateUserDTO.teamId()).build());
        userProfile.setWeight(updateUserDTO.weight());
        userProfile.setHeight(updateUserDTO.height());
        if (updateUserDTO.socialNetworks() != null) {
            updateUserDTO.socialNetworks()
                    .forEach(socialNetworkDTO -> {
                        SocialNetwork socialNetwork = new SocialNetwork();
                        socialNetwork.setId(socialNetworkDTO.id());
                        socialNetwork.setName(socialNetworkDTO.name());
                        socialNetwork.setAccount(socialNetworkDTO.account());
                        userProfile.addSocialNetwork(socialNetwork);
                    });
        }
        userProfile.setPhoto(updateUserDTO.avatar() != null ? Base64.getDecoder().decode(updateUserDTO.avatar()) : null);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findUserWithProfileAndSocialNetworksById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
        if (updateUserDTO.role() != RoleEnum.ADMIN && userRepository.countUserWithRoleByRoleId(RoleConstants.ADMIN) <= 1)
            throw new UnacceptableDataException("В системе должен оставаться хотя бы 1 администратор.");
        switch (updateUserDTO.role()) {
            case ADMIN -> user.setRole(Role.builder().id(RoleConstants.ADMIN).build());
            case SPORTSMAN -> user.setRole(Role.builder().id(RoleConstants.SPORTSMAN).build());
            case DABBLER -> user.setRole(Role.builder().id(RoleConstants.DABBLER).build());
        }
        UserProfile userProfile = user.getUserProfile();
        uniqueEmailValidator(userProfile.getId(), updateUserDTO.email());
        userProfile.setEmail(updateUserDTO.email());
        userProfile.setGender(updateUserDTO.gender());
        userProfile.setBirthDate(updateUserDTO.birthDate());
        userProfile.setFirstName(updateUserDTO.firstName());
        userProfile.setLastName(updateUserDTO.lastName());
        userProfile.setSurname(updateUserDTO.surname());
        if (updateUserDTO.cityId() != null) userProfile.setCity(City.builder().id(updateUserDTO.cityId()).build());
        if (updateUserDTO.teamId() != null) userProfile.setTeam(Team.builder().id(updateUserDTO.teamId()).build());
        userProfile.setWeight(updateUserDTO.weight());
        userProfile.setHeight(updateUserDTO.height());

        if (updateUserDTO.socialNetworks() != null) {
            updateUserDTO.socialNetworks()
                    .forEach(socialNetworkDTO -> {
                        SocialNetwork socialNetwork = new SocialNetwork();
                        socialNetwork.setId(socialNetworkDTO.id());
                        socialNetwork.setName(socialNetworkDTO.name());
                        socialNetwork.setAccount(socialNetworkDTO.account());
                        userProfile.addSocialNetwork(socialNetwork);
                    });
        }

        userProfile.setPhoto(updateUserDTO.avatar() != null ? Base64.getDecoder().decode(updateUserDTO.avatar()) : null);

        //TODO поидее должно работать без save
        userRepository.save(user);
    }

    @Override
    public User getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNumber = authentication.getName();
        return userRepository.findUserWithProfileByPhoneNumber(phoneNumber).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findUserWithProfileById(id).orElseThrow(() -> new ObjectNotFound(ErrorMessages.UserErrors.NOT_FOUND));
    }


//    @Override
//    public ResponseEntity<?> restorePassword(LoginDto loginDto) {
//        User user = iUserRepository.findUserByPhoneNumber(loginDto.phoneNumber()).orElseThrow(() -> new RestoreUserNotFound("Такой пользователь не существует"));
//        user.setPassword(passwordEncoder.encode(loginDto.password()));
//        iUserRepository.save(user);
//
//        return new ResponseEntity<>("Пароль изменен.", HttpStatus.OK);
//
//    }
}
