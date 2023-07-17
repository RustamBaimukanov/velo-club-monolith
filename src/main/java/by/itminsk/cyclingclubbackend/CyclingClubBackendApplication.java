package by.itminsk.cyclingclubbackend;

import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.user.Role;
import by.itminsk.cyclingclubbackend.model.user.Trophy;
import by.itminsk.cyclingclubbackend.model.user.User;
import by.itminsk.cyclingclubbackend.repository.RoleRepository;
import by.itminsk.cyclingclubbackend.repository.TrophyRepository;
import by.itminsk.cyclingclubbackend.repository.UserRepository;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.trophy.TrophyService;
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


    @Bean
    CommandLineRunner run (UserService iUserService , RoleRepository iRoleRepository , UserRepository iUserRepository , PasswordEncoder passwordEncoder, TrophyRepository trophyRepository, TrophyService trophyService)
    {return  args ->
    {   iUserService.saveRole(new Role("USER"));
        iUserService.saveRole(new Role("ADMIN"));
        iUserService.saveRole(new Role("SUPERADMIN"));
        iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("1111"), new HashSet<>()));
        iUserService.saverUser(new User("superadminadmin@gmail.com", passwordEncoder.encode("superadminPassword"), new HashSet<>()));


        Trophy trophy = new Trophy();
        trophy.setName("Золотой кубок");
        trophyService.addTrophy(trophy);

        Role role = iRoleRepository.findRoleByName("ADMIN");
        User user = iUserRepository.findUserByEmail("admin@gmail.com").orElse(null);
        user.getRoles().add(role);
        iUserService.saverUser(user);

        User userr = iUserRepository.findUserByEmail("superadminadmin@gmail.com").orElse(null);
        Role rolee = iRoleRepository.findRoleByName("SUPERADMIN");
        userr.getRoles().add(rolee);
        iUserService.saverUser(userr);
        User regularUser = iUserService.registerAuto(new RegisterDto("Имя", "Фамилия", "+375251111111", "test@mail.ru", "111111", "111111", true, null, null, null));





        System.out.println(regularUser.getEmail());
        System.out.println(regularUser.getRoles().toString());
        System.out.println(regularUser.getTrophies().toString());



    };
    }

}
