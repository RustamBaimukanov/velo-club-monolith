package by.itminsk.cyclingclubbackend;

import by.itminsk.cyclingclubbackend.model.user.Role;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.repository.RoleRepository;
import by.itminsk.cyclingclubbackend.repository.UserRepository;
import by.itminsk.cyclingclubbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
        //(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class CyclingClubBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyclingClubBackendApplication.class, args);
    }


//    @Bean
//    CommandLineRunner run (UserService iUserService , RoleRepository iRoleRepository , UserRepository iUserRepository , PasswordEncoder passwordEncoder)
//    {return  args ->
//    {   iUserService.saveRole(new Role("USER"));
//        iUserService.saveRole(new Role("ADMIN"));
//        iUserService.saveRole(new Role("SUPERADMIN"));
//        iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("adminPassword"), new HashSet<>()));
//        iUserService.saverUser(new User("superadminadmin@gmail.com", passwordEncoder.encode("superadminPassword"), new HashSet<>()));
//
//        Role role = iRoleRepository.findRoleByName("ADMIN");
//        User user = iUserRepository.findUserByEmail("admin@gmail.com").orElse(null);
//        user.getRoles().add(role);
//        iUserService.saverUser(user);
//
//        User userr = iUserRepository.findUserByEmail("superadminadmin@gmail.com").orElse(null);
//        Role rolee = iRoleRepository.findRoleByName("SUPERADMIN");
//        userr.getRoles().add(rolee);
//        iUserService.saverUser(userr);
//
//    };
//    }

}
