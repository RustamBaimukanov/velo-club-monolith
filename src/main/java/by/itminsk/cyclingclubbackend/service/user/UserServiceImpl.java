package by.itminsk.cyclingclubbackend.service.user;

import by.itminsk.cyclingclubbackend.model.city.City;
import by.itminsk.cyclingclubbackend.model.role.RoleEnum;
import by.itminsk.cyclingclubbackend.model.role.RolesEnum;
import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.repository.user.UserRepository;
import by.itminsk.cyclingclubbackend.service.social_network.SocialNetworkService;
import by.itminsk.cyclingclubbackend.model.team.Team;
import by.itminsk.cyclingclubbackend.model.event_result.EventResult;
import by.itminsk.cyclingclubbackend.repository.event_result.EventResultsRepository;
import by.itminsk.cyclingclubbackend.util.ImageUtil;
import by.itminsk.cyclingclubbackend.util.exception_handler.*;
import by.itminsk.cyclingclubbackend.model.role.Role;
import by.itminsk.cyclingclubbackend.repository.role.RoleRepository;
import by.itminsk.cyclingclubbackend.security.JwtUtilities;
import by.itminsk.cyclingclubbackend.service.city.CityService;
import by.itminsk.cyclingclubbackend.repository.social_network.SocialNetworkRepository;
import by.itminsk.cyclingclubbackend.service.team.TeamService;
import by.itminsk.cyclingclubbackend.service.trophy.TrophyService;
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
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TrophyService trophyService;

    private final SocialNetworkService socialNetworkService;

    private final CityService cityService;

    private final TeamService teamService;

    private final EventResultsRepository eventResultsRepository;

    private final SocialNetworkRepository socialNetworkRepository;


    private final AuthenticationManager authenticationManager;
    private final UserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;


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
    public void createAdmin() {
        User user = new User();
        user.setEmail("Ruler");
        //user.setPassword(this.passwordEncoder.encode("1111"));
        user.setRole(roleRepository.findRoleByName(RoleEnum.ADMIN));
        userRepository.save(user);
    }


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User saverUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public Boolean existByPhoneNumber(String phoneNumber) {
        return iUserRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean existByEmail(String email) {
        if (email == null) return false;
        return iUserRepository.existsByEmail(email);
    }


    @Override
    public User getUser(String phoneNumber) {
        return iUserRepository.findUserByPhoneNumber(phoneNumber).orElse(new User());
    }

    @Override
    public UserInfoDTO getUserInfo(String phoneNumber) {
        UserInfoDTO userInfoDTO = iUserRepository.getUserByPhoneNumber(phoneNumber);
        userInfoDTO.setSocialNetworks(socialNetworkRepository.findAllByUserId(userInfoDTO.getId()));
        userInfoDTO.setTrophies(trophyService.findAllByUserId(userInfoDTO.getId()));
        userInfoDTO.setEventResults(eventResultsRepository.findAllByUserId(userInfoDTO.getId()));
        userInfoDTO.setQualification(roleRepository.findRoleByUser(userInfoDTO.getId()));
        Map<Integer, List<EventResult>> eventMap = userInfoDTO
                .getEventResults()
                .stream()
                .peek(eventResult -> {
                    eventResult.setDate(eventResult.getEvent().getDate());
                    eventResult.setName(eventResult.getEvent().getName());
                })
                .collect(Collectors.groupingBy(eventResult -> {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(eventResult.getEvent().getDate());
                            return calendar.get(Calendar.YEAR);
                        }, LinkedHashMap::new, Collectors.toList()
                ));
        Map<Integer, List<EventResult>> integerListMap = new LinkedHashMap<>();
        List<Integer> integerList = eventMap.keySet().stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).toList();
        for (Integer year :
                integerList) {
            integerListMap.put(year, eventMap.get(year));
        }
        userInfoDTO.setEvent(integerListMap);
        return userInfoDTO;
    }

    @Override
    public EditUserDTO getEditableUser(String phoneNumber) {
        EditUserDTO editUserDTO = iUserRepository.findByPhoneNumber(phoneNumber);
        editUserDTO.setSocialNetworks(socialNetworkRepository.findAllByUserId(editUserDTO.getId()));
        editUserDTO.setQualification(userRepository.findRoleByUserId(editUserDTO.getId()));
        return editUserDTO;
    }

    @Override
    public UserMenuDTO getUserMenu(String phoneNumber) {
        return iUserRepository.getUserMenuByPhoneNumber(phoneNumber);
    }

    @Override
    public ResponseEntity<?> editUser(UpdateUserDTO updateUserDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = iUserRepository.findUserByPhoneNumber(currentPrincipalName);
        if (updateUserDTO.getEmail() != null) {
            if (updateUserDTO.getEmail().trim().equals("")) updateUserDTO.setEmail(null);
        }
        if (iUserRepository.existsByEmailAndEmailIsNotNull(updateUserDTO.getEmail())
                && !updateUserDTO.getEmail().equals(user.get().getEmail())) {
            throw new UniqueObjectExistException("Пользователь с данным email уже существует!");
        }
//        iUserRepository.findUserByEmailAndEmailIsNotNull(updateUserDTO.getEmail()).if
        user.ifPresent(u -> {
            u.setFirstName(updateUserDTO.getFirstName());
            u.setLastName(updateUserDTO.getLastName());
            u.setEmail(updateUserDTO.getEmail());
            u.setBirthDate(updateUserDTO.getBirth());
            u.setSex(updateUserDTO.getGender());
            u.setHeight(updateUserDTO.getHeight());
            u.setWeight(updateUserDTO.getWeight());
            u.setCity(City.builder().id(updateUserDTO.getRegion()).build());
//            u.setTeam(Team.builder().id(updateUserDTO.getClub()).build());
            if (u.getRole().getName() == RoleEnum.ADMIN && updateUserDTO.getQualification() != RoleEnum.ADMIN && Objects.equals(currentPrincipalName, u.getPhoneNumber()))
                throw new UnacceptableDataException("Администратор не может лишить себя прав администратора.");
            switch (updateUserDTO.getImageStatus()) {
                case CHANGE_IMG -> {
                    try {
                        u.setPhoto(ImageUtil.compressAndEncodeImage(updateUserDTO.getUserImg()));
                        u.setPhotoFormat(updateUserDTO.getUserImg().getContentType());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case DELETE_IMG -> {
                    u.setPhoto(null);
                    u.setPhotoFormat(null);
                }
            }
            socialNetworkService.saveSocialNetworksWhenUserEdit(u, updateUserDTO.getSocialNetworks());
            iUserRepository.save(u);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editUserByAdmin(Long id, UpdateUserDTO updateUserDTO) {
        log.info("Email is : {}", updateUserDTO.getEmail());
        if (updateUserDTO.getEmail() != null) {
            updateUserDTO.setEmail(updateUserDTO.getEmail().trim().equals("") ? null : updateUserDTO.getEmail());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = iUserRepository.findById(id);
        if (iUserRepository.existsByEmailAndEmailIsNotNull(updateUserDTO.getEmail())
                && !updateUserDTO.getEmail().equals(user.get().getEmail())) {
            throw new UniqueObjectExistException("Пользователь с данным email уже существует!");
        }


        user.ifPresent(u -> {
            u.setFirstName(updateUserDTO.getFirstName());
            u.setLastName(updateUserDTO.getLastName());
            u.setEmail(updateUserDTO.getEmail());
            u.setBirthDate(updateUserDTO.getBirth());
            u.setSex(updateUserDTO.getGender());
            u.setHeight(updateUserDTO.getHeight());
            u.setWeight(updateUserDTO.getWeight());
            u.setCity(City.builder().id(updateUserDTO.getRegion()).build());
            if (updateUserDTO.getClub() != null)
                u.setTeam(Team.builder().id(updateUserDTO.getClub()).build());
            if (u.getRole().getName() == RoleEnum.ADMIN && updateUserDTO.getQualification() != RoleEnum.ADMIN && Objects.equals(currentPrincipalName, u.getPhoneNumber()))
                throw new UnacceptableDataException("Администратор не может лишить себя прав администратора.");
            switch (updateUserDTO.getImageStatus()) {
                case CHANGE_IMG -> {
                    try {
                        u.setPhoto(ImageUtil.compressAndEncodeImage(updateUserDTO.getUserImg()));
                        u.setPhotoFormat(updateUserDTO.getUserImg().getContentType());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case NO_CHANGE_IMG -> {

                }
                case DELETE_IMG -> {
                    u.setPhoto(null);
                    u.setPhotoFormat(null);
                }
            }
            socialNetworkService.saveSocialNetworksWhenUserEdit(u, updateUserDTO.getSocialNetworks());
            iUserRepository.save(u);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public Long getIdFromPhoneNumber(String phoneNumber) {
        return iUserRepository.getIdFromPhoneNumber(phoneNumber);
    }

    @Override
    public UserGetDto getUser(Long id) {
        return userRepository.findUserDtoById(id).orElseThrow(() -> new ObjectNotFound("Пользователь не найден."));
    }

    @Override
    public List<UserGetDto> getUser() {
        return userRepository.findAllUserDto();
    }

    @Override
    public List<UserGetDto> getUsersExceptRole(RolesEnum role) {
        switch (role) {
            default -> {
                return new ArrayList<>();
            }
            case SPORTSMAN -> {
                return userRepository.findAllByRoleName(RoleEnum.DABBLER);
            }
            case DABBLER -> {
                return userRepository.findAllByRoleName(RoleEnum.SPORTSMAN);
            }
        }
    }

    @Override
    public void sameUserValidator(Long userId, String phoneNumber) {
        User user = findUserByPhoneNumber(phoneNumber);
        if (!(user.getRole().getName() == RoleEnum.ADMIN) && !user.getId().equals(userId)) {
            throw new PermissionException("Данный пользователь не имеет соответствующих прав доступа.");
        }
    }

    @Override
    public void userExistValidator(Long id) {
        if (!userRepository.existsById(id)) throw new ObjectNotFound("Пользователь не найден.");
    }

    @Override
    public void userExistValidator(Set<Long> ids) {
        if (!userRepository.existsByIdIn(ids)) throw new ObjectNotFound("Пользователи не найдены.");

    }

    @Override
    public void uniqueUserValidator(String phoneNumber, String email) {
        if (existByPhoneNumber(phoneNumber) && (existByEmail(email))) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном и почтой уже зарегистрирован!");
        } else if (existByEmail(email)) {
            throw new UniqueObjectExistException("Пользователь с данным email уже зарегистрирован!");
        } else if (existByPhoneNumber(phoneNumber)) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном уже зарегистрирован!");

            //return userService.registerByAdmin(registerDto);
        }
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new ObjectNotFound("Пользователь не найден."));
    }

    @Override
    public UserInfoDTO getUserProfile(Long id) {
        UserInfoDTO userInfoDTO = iUserRepository.getUserById(id);
        userInfoDTO.setSocialNetworks(socialNetworkRepository.findAllByUserId(userInfoDTO.getId()));
        userInfoDTO.setTrophies(trophyService.findAllByUserId(userInfoDTO.getId()));
        userInfoDTO.setEventResults(eventResultsRepository.findAllByUserId(userInfoDTO.getId()));
        userInfoDTO.setQualification(roleRepository.findRoleByUser(userInfoDTO.getId()));
        Map<Integer, List<EventResult>> eventMap = userInfoDTO
                .getEventResults()
                .stream()
                .peek(eventResult -> {
                    eventResult.setDate(eventResult.getEvent().getDate());
                    eventResult.setName(eventResult.getEvent().getName());
                })
                .collect(Collectors.groupingBy(eventResult -> {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(eventResult.getEvent().getDate());
                            return calendar.get(Calendar.YEAR);
                        }, LinkedHashMap::new, Collectors.toList()
                ));
        Map<Integer, List<EventResult>> integerListMap = new LinkedHashMap<>();
        List<Integer> integerList = eventMap.keySet().stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).toList();
        for (Integer year :
                integerList) {
            integerListMap.put(year, eventMap.get(year));
        }
        userInfoDTO.setEvent(integerListMap);
        return userInfoDTO;
    }

    @Override
    public ResponseEntity<?> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        iUserRepository.saveAndFlush(user);
        trophyService.addTrophy(1L, 6L, user, "Выдано за регистрацию");
        String token = jwtUtilities.generateToken(user.getPhoneNumber(), Collections.singletonList(RoleEnum.DABBLER.name()));
        return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
    }

    @Override
    public void registerByAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        iUserRepository.saveAndFlush(user);
        trophyService.addTrophy(1L, 6L, user, "Выдано за регистрацию");
    }

    @Override
    public Boolean confirmPassword(String password, String confirmPassword) {
        return (Objects.equals(password, confirmPassword));
    }

    @Override
    public ResponseEntity<?> changePhoneNumber(String oldPhoneNumber, String newPhoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findUserByPhoneNumber(oldPhoneNumber).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getTel(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = iUserRepository.findUserByPhoneNumber(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Некорректный номер телефона или пароль."));
        return new ResponseEntity<>(new BearerToken(jwtUtilities.generateToken(user.getUsername(), Collections.singletonList(user.getRole().getName().name())), "Bearer "), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> restorePassword(LoginDto loginDto) {
        User user = iUserRepository.findUserByPhoneNumber(loginDto.getTel()).orElseThrow(() -> new RestoreUserNotFound("Такой пользователь не существует"));
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        iUserRepository.save(user);

        return new ResponseEntity<>("Пароль изменен.", HttpStatus.OK);

    }
}
