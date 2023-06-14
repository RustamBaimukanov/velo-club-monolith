package by.itminsk.cyclingclubbackend.service;

import by.itminsk.cyclingclubbackend.dto.BearerToken;
import by.itminsk.cyclingclubbackend.dto.LoginDto;
import by.itminsk.cyclingclubbackend.dto.RegisterByAdminDto;
import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.user.Role;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.model.user.UserDTO;
import by.itminsk.cyclingclubbackend.repository.RoleRepository;
import by.itminsk.cyclingclubbackend.repository.TeamRepository;
import by.itminsk.cyclingclubbackend.repository.UserRepository;
import by.itminsk.cyclingclubbackend.security.JwtUtilities;
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

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TeamRepository teamRepository;



    private final AuthenticationManager authenticationManager;
    private final UserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if (iUserRepository.existsByPhoneNumber(registerDto.getPhoneNumber())) {
            return new ResponseEntity<>("Номер телефона уже зарегистрирован на портале!", HttpStatus.SEE_OTHER);
        } else {
            if (confirmPassword(registerDto.getPassword(), registerDto.getConfirmPassword())) {
                User user = new User();
                user.setPhoneNumber(registerDto.getPhoneNumber());
                user.setEmail(registerDto.getEmail());
                user.setFirstName(registerDto.getFirstName());
                user.setLastName(registerDto.getLastName());
                user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
                Role role = roleRepository.findRoleByName("USER");
                user.addRole(role);
                iUserRepository.save(user);
                String token = jwtUtilities.generateToken(registerDto.getEmail(), Collections.singletonList(role.getName()));
                return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    @Override
    public ResponseEntity<?> registerByAdmin(RegisterByAdminDto registerByAdminDto) {
        if (iUserRepository.existsByPhoneNumber(registerByAdminDto.getPhoneNumber())) {
            return new ResponseEntity<>("Номер телефона уже зарегистрирован на портале!", HttpStatus.SEE_OTHER);
        } else {
            User user = new User();
            user.setPhoneNumber(registerByAdminDto.getPhoneNumber());
            user.setEmail(registerByAdminDto.getEmail());
            user.setFirstName(registerByAdminDto.getFirstName());
            user.setLastName(registerByAdminDto.getLastName());
            user.setPassword(passwordEncoder.encode(registerByAdminDto.getPassword()));
            user.setBirthDate(registerByAdminDto.getBirthDate());
            user.setSex(registerByAdminDto.getSex());
            user.setHeight(registerByAdminDto.getHeight());
            user.setWeight(registerByAdminDto.getWeight());
            user.setAddress(registerByAdminDto.getAddress());
            user.setTeam(teamRepository.findTeamById(registerByAdminDto.getTeamId()));



            Role role = roleRepository.findRoleByName("USER");
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
    public String authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getPhoneNumber(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = iUserRepository.findUserByPhoneNumber(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getName()));
        System.out.println(user.getEmail());
        String token = jwtUtilities.generateToken(user.getUsername(), rolesNames);
        return token;
    }


}
