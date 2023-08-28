package by.itminsk.cyclingclubbackend;

import by.itminsk.cyclingclubbackend.dto.RegisterDto;
import by.itminsk.cyclingclubbackend.model.user.*;
import by.itminsk.cyclingclubbackend.repository.RoleRepository;
import by.itminsk.cyclingclubbackend.repository.SocialNetworkRepository;
import by.itminsk.cyclingclubbackend.repository.TrophyRepository;
import by.itminsk.cyclingclubbackend.repository.UserRepository;
import by.itminsk.cyclingclubbackend.service.UserService;
import by.itminsk.cyclingclubbackend.service.trophy.TrophyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
        //(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class CyclingClubBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyclingClubBackendApplication.class, args);
    }


    @Bean
    CommandLineRunner run (UserService iUserService , RoleRepository iRoleRepository , UserRepository iUserRepository , PasswordEncoder passwordEncoder, TrophyRepository trophyRepository, TrophyService trophyService, SocialNetworkRepository socialNetworkRepository)
    {return  args ->
    {
        iUserService.saveRole(new Role("ADMIN"));
        iUserService.saveRole(new Role("Велоспортсмен"));
        iUserService.saveRole(new Role("Велолюбитель"));

        iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("1111"), new HashSet<>()));


        Trophy trophy = new Trophy();
        trophy.setName("Золотой кубок");
        trophyService.addTrophy(trophy);

        Role role = iRoleRepository.findRoleByName("ADMIN");
        User user = iUserRepository.findUserByEmail("admin@gmail.com").orElse(null);
        user.getRoles().add(role);
        iUserService.saverUser(user);


        Map<String, String> socialNetwork = new HashMap<>();
        socialNetwork.put("viber", "+375251111111");
        socialNetwork.put("vk", "id334414521");

        User test = iUserService.registerAuto(new RegisterDto("Имя", "Фамилия", "+375251111111", "test@mail.ru", "111111", "111111", true, new Date(), "male", null, socialNetwork));
        System.out.println(test.getId());
        Set<SocialNetwork> socialNetworks = new HashSet<>();
        socialNetwork.forEach((name, link) ->{
            socialNetworks.add(new SocialNetwork(new SocialNetworkDTO(name, link, test)));
        });
        socialNetworkRepository.saveAll(socialNetworks);








    };
    }

}
