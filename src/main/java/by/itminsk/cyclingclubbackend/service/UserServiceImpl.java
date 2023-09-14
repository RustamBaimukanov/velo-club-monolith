package by.itminsk.cyclingclubbackend.service;

import by.itminsk.cyclingclubbackend.dto.BearerToken;
import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.exception_handler.RestoreUserNotFound;
import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.repository.*;
import by.itminsk.cyclingclubbackend.security.JwtUtilities;
import by.itminsk.cyclingclubbackend.service.city.CityService;
import by.itminsk.cyclingclubbackend.service.team.TeamService;
import by.itminsk.cyclingclubbackend.service.trophy.TrophyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TrophyService trophyService;

    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private EventResultsRepository eventResultsRepository;


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
        user.addRole(roleRepository.findRoleByName("admin"));
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
    public ResponseEntity<?> existByPhoneNumberAndEmail(String phoneNumber, String email) {
        Boolean emailExist;
        Boolean phoneExist;
        if (email != null) {
            emailExist = iUserRepository.existsByEmail(email);
            phoneExist = iUserRepository.existsByPhoneNumber(phoneNumber);
            if (emailExist && phoneExist) {
                return new ResponseEntity<>("Пользователь с таким номером и почтой существует.", HttpStatus.NOT_ACCEPTABLE);
            } else if (!emailExist && phoneExist) {
                return new ResponseEntity<>("Пользователь с таким номером существует.", HttpStatus.NOT_ACCEPTABLE);
            } else if (emailExist) {
                return new ResponseEntity<>("Пользователь с такой почтой существует.", HttpStatus.NOT_ACCEPTABLE);
            } else {
                new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return iUserRepository.existsByPhoneNumber(phoneNumber) ?
                new ResponseEntity<>("Пользователь с таким номером существует.", HttpStatus.NOT_ACCEPTABLE) :
                new ResponseEntity<>(HttpStatus.OK);

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
                ) );
        Map<Integer, List<EventResult>> integerListMap = new LinkedHashMap<>();
        List<Integer> integerList = eventMap.keySet().stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).toList();
        for (Integer year:
             integerList) {
            integerListMap.put(year, eventMap.get(year));
        }
        userInfoDTO.setEvent(integerListMap);
        return userInfoDTO;
    }

    @Override
    public Long getIdFromPhoneNumber(String phoneNumber) {
        return iUserRepository.getIdFromPhoneNumber(phoneNumber);
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        registerDto.setEmail(registerDto.getEmail().trim());
        if (iUserRepository.existsByPhoneNumber(registerDto.getTel()) && (iUserRepository.existsByEmail(registerDto.getEmail()) && registerDto.getEmail().length() != 0)) {
            return new ResponseEntity<>("Пользователь с таким телефоном и почтой уже зарегистрирован!", HttpStatus.SEE_OTHER);
        } else if (iUserRepository.existsByEmail(registerDto.getEmail()) && registerDto.getEmail().length() != 0) {
            return new ResponseEntity<>("Пользователь с данным email уже зарегистрирован!", HttpStatus.SEE_OTHER);
        } else if (iUserRepository.existsByPhoneNumber(registerDto.getTel())) {
            return new ResponseEntity<>("Пользователь с таким телефоном уже зарегистрирован!", HttpStatus.SEE_OTHER);
        } else {
            User user = new User();
            user.setPhoneNumber(registerDto.getTel());
            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setBirthDate(registerDto.getBirth());
            user.setSex(registerDto.getGender());
            user.setCity(cityService.getCity(registerDto.getCity()));
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            Role role = roleRepository.findRoleByName("DABBLER");
            user.addRole(role);
            iUserRepository.saveAndFlush(user);
            trophyService.addTrophy(1L, 6L, user, "Выдано за регистрацию");
            String token = jwtUtilities.generateToken(registerDto.getTel(), Collections.singletonList(role.getName()));
            return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);

        }
    }

    @Override
    public User registerAuto(RegisterDto registerDto) {
        if (iUserRepository.existsByPhoneNumber(registerDto.getTel())) {
            return null;
        } else {
            if (confirmPassword(registerDto.getPassword(), registerDto.getConfirmPassword())) {
                User user = new User();
                user.setPhoneNumber(registerDto.getTel());
                user.setEmail(registerDto.getEmail());
                user.setFirstName(registerDto.getFirstName());
                user.setLastName(registerDto.getLastName());
                user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
                user.setBirthDate(registerDto.getBirth());
                user.setSex(registerDto.getGender());
                Role role = roleRepository.findRoleByName("DABBLER");
                //Trophy trophy = trophyRepository.findTrophyByName("Золотой кубок");
                user.addRole(role);
                //user.addTrophy(trophy);

                user.setCity(cityService.getCityById(1L));
                user.setTeam(teamService.getTeamById(1L));
                return iUserRepository.saveAndFlush(user);
            } else {
                return null;
            }
        }
    }

    @Override
    public ResponseEntity<?> registerByAdmin(RegisterByAdminDto registerByAdminDto) throws IOException {
        registerByAdminDto.setEmail(registerByAdminDto.getEmail());
        if (iUserRepository.existsByPhoneNumber(registerByAdminDto.getTel()) && (iUserRepository.existsByEmail(registerByAdminDto.getEmail()) && registerByAdminDto.getEmail().length() != 0)) {
            return new ResponseEntity<>("Пользователь с таким телефоном и почтой уже зарегистрирован!", HttpStatus.SEE_OTHER);
        } else if (iUserRepository.existsByEmail(registerByAdminDto.getEmail()) && registerByAdminDto.getEmail().length() != 0) {
            return new ResponseEntity<>("Пользователь с данным email уже зарегистрирован!", HttpStatus.SEE_OTHER);
        } else if (iUserRepository.existsByPhoneNumber(registerByAdminDto.getTel())) {
            return new ResponseEntity<>("Пользователь с таким телефоном уже зарегистрирован!", HttpStatus.SEE_OTHER);
        } else {
            User user = new User();
            user.setPhoneNumber(registerByAdminDto.getTel());
            user.setEmail(registerByAdminDto.getEmail());
            user.setFirstName(registerByAdminDto.getFirstName());
            user.setLastName(registerByAdminDto.getLastName());
            user.setPassword(passwordEncoder.encode(registerByAdminDto.getPassword()));
            user.setBirthDate(registerByAdminDto.getBirth());
            user.setSex(registerByAdminDto.getGender());
            user.setHeight(registerByAdminDto.getHeight());
            user.setWeight(registerByAdminDto.getWeight());
            user.setAddress(registerByAdminDto.getAddress());
            user.setTeam(teamService.getTeam(registerByAdminDto.getClub()));
            user.setCity(cityService.getCity(registerByAdminDto.getRegion()));
            if (registerByAdminDto.getUserImg() != null) {
                user.setPhoto(registerByAdminDto.getUserImg().getBytes());
                user.setPhotoFormat(registerByAdminDto.getUserImg().getContentType());
            }
            //Trophy trophy = trophyRepository.findTrophyByName("Золотой кубок");
            //user.addTrophy(trophy);
            Role role = roleRepository.findRoleByName(registerByAdminDto.getQualification());
            user.addRole(role);
            iUserRepository.save(user);
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
        User user = iUserRepository.findUserByPhoneNumber(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getName()));
        return new ResponseEntity<>(new BearerToken(jwtUtilities.generateToken(user.getUsername(), rolesNames), "Bearer "), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> restorePassword(LoginDto loginDto) throws RestoreUserNotFound {
        User user = iUserRepository.findUserByPhoneNumber(loginDto.getTel()).orElseThrow(() -> new RestoreUserNotFound("Такой пользователь не существует"));
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        iUserRepository.save(user);

        return new ResponseEntity<>("Пароль изменен.", HttpStatus.OK);

    }
}
