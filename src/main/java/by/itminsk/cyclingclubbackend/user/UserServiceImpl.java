package by.itminsk.cyclingclubbackend.user;

import by.itminsk.cyclingclubbackend.r_city.City;
import by.itminsk.cyclingclubbackend.role.dto.RoleEnum;
import by.itminsk.cyclingclubbackend.social_network.SocialNetwork;
import by.itminsk.cyclingclubbackend.social_network.SocialNetworkEnum;
import by.itminsk.cyclingclubbackend.social_network.SocialNetworkService;
import by.itminsk.cyclingclubbackend.team.Team;
import by.itminsk.cyclingclubbackend.trophy.Trophy;
import by.itminsk.cyclingclubbackend.user.dto.BearerToken;
import by.itminsk.cyclingclubbackend.user.dto.LoginDto;
import by.itminsk.cyclingclubbackend.user.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.user.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.event.EventResult;
import by.itminsk.cyclingclubbackend.event.EventResultsRepository;
import by.itminsk.cyclingclubbackend.util.ImageUtil;
import by.itminsk.cyclingclubbackend.util.exception_handler.*;
import by.itminsk.cyclingclubbackend.role.Role;
import by.itminsk.cyclingclubbackend.role.RoleRepository;
import by.itminsk.cyclingclubbackend.util.security.JwtUtilities;
import by.itminsk.cyclingclubbackend.r_city.CityService;
import by.itminsk.cyclingclubbackend.social_network.SocialNetworkRepository;
import by.itminsk.cyclingclubbackend.team.TeamService;
import by.itminsk.cyclingclubbackend.trophy.TrophyService;
import by.itminsk.cyclingclubbackend.user.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public void registration(UserDTO userDTO) {

        User user = new User();
        //user.setEmail(userDTO.getEmail());
        // user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
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
        log.info(String.valueOf(updateUserDTO.getImageStatus()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = iUserRepository.findUserByPhoneNumber(currentPrincipalName);
        if (updateUserDTO.getEmail() != null){
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

            switch (updateUserDTO.getImageStatus()){
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
    @Transactional
    public ResponseEntity<?> editUserByAdmin(UpdateUserDTO updateUserDTO) {
        log.info(String.valueOf(updateUserDTO.getImageStatus()));

        if (updateUserDTO.getEmail() != null){
            updateUserDTO.setEmail(updateUserDTO.getEmail().trim().equals("") ? null : updateUserDTO.getEmail());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = iUserRepository.findUserByPhoneNumber(updateUserDTO.getTel());
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
            switch (updateUserDTO.getImageStatus()){
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
    public List<UserGetDto> getUsersExceptRole(RoleEnum role) {
        return userRepository.findAllByRoleNameNot(role);
    }

    @Override
    public void sameUserValidator(Long userId, String phoneNumber) {
        User user = findUserByPhoneNumber(phoneNumber);
        if (!(user.getRole().getName() == RoleEnum.ADMIN) && !user.getId().equals(userId)){
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
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new ObjectNotFound("Пользователь не найден."));
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if (registerDto.getEmail() != null){
            registerDto.setEmail(registerDto.getEmail().trim().equals("") ? null : registerDto.getEmail());
        }
        if (iUserRepository.existsByPhoneNumber(registerDto.getTel()) && (iUserRepository.existsByEmail(registerDto.getEmail())) && registerDto.getEmail() != null) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном и почтой уже зарегистрирован!");
        } else if (iUserRepository.existsByEmail(registerDto.getEmail()) && registerDto.getEmail() != null) {
            throw new UniqueObjectExistException("Пользователь с данным email уже зарегистрирован!");
        } else if (iUserRepository.existsByPhoneNumber(registerDto.getTel())) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном уже зарегистрирован!");
        } else {
            User user = new User();
            user.setPhoneNumber(registerDto.getTel());
            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setBirthDate(registerDto.getBirth());
            user.setSex(registerDto.getGender());
            user.setCity(City.builder().id(registerDto.getCity()).build());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            Role role = roleRepository.findRoleByName(RoleEnum.DABBLER);
            user.setRole(role);
            iUserRepository.saveAndFlush(user);
            trophyService.addTrophy(1L, 6L, user, "Выдано за регистрацию");
            String token = jwtUtilities.generateToken(registerDto.getTel(), Collections.singletonList(role.getName().name()));
            return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<?> registerByAdmin(RegisterByAdminDto registerByAdminDto) throws IOException {
        if (registerByAdminDto.getEmail() != null){
            registerByAdminDto.setEmail(registerByAdminDto.getEmail().trim().equals("") ? null : registerByAdminDto.getEmail());
        }
        if (iUserRepository.existsByPhoneNumber(registerByAdminDto.getTel()) && (iUserRepository.existsByEmail(registerByAdminDto.getEmail())) && registerByAdminDto.getEmail() != null) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном и почтой уже зарегистрирован!");
        } else if (iUserRepository.existsByEmail(registerByAdminDto.getEmail()) && registerByAdminDto.getEmail() != null) {
            throw new UniqueObjectExistException("Пользователь с данным email уже зарегистрирован!");
        } else if (iUserRepository.existsByPhoneNumber(registerByAdminDto.getTel())) {
            throw new UniqueObjectExistException("Пользователь с таким телефоном уже зарегистрирован!");
        } else {
            User user = new User();
            user.setPhoneNumber(registerByAdminDto.getTel());
            log.warn(registerByAdminDto.getEmail());
            user.setEmail(registerByAdminDto.getEmail());
            user.setFirstName(registerByAdminDto.getFirstName());
            user.setLastName(registerByAdminDto.getLastName());
            user.setPassword(passwordEncoder.encode(registerByAdminDto.getPassword()));
            user.setBirthDate(registerByAdminDto.getBirth());
            user.setSex(registerByAdminDto.getGender());
            user.setHeight(registerByAdminDto.getHeight());
            user.setWeight(registerByAdminDto.getWeight());
            user.setAddress(registerByAdminDto.getAddress());
            if (registerByAdminDto.getClub() != null)
                user.setTeam(Team.builder().id(registerByAdminDto.getClub()).build());
            user.setCity(City.builder().id(registerByAdminDto.getRegion()).build());
            if (registerByAdminDto.getUserImg() != null) {
                user.setPhoto(registerByAdminDto.getUserImg().getBytes());
                user.setPhotoFormat(registerByAdminDto.getUserImg().getContentType());
            }
            else {
                user.setPhoto(null);
                user.setPhotoFormat(null);
            }
            Role role = roleRepository.findRoleByName(registerByAdminDto.getQualification());
            user.setRole(role);
            iUserRepository.saveAndFlush(user);
            trophyService.addTrophy(1L, 6L, user, "Выдано за регистрацию");
            return new ResponseEntity<>(HttpStatus.OK);
        }
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
