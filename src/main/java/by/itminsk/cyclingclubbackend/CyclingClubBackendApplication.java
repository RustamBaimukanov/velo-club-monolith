package by.itminsk.cyclingclubbackend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@SpringBootApplication
        //(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class CyclingClubBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyclingClubBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("http://localhost:8082", "http://localhost:4200", "http://localhost:8080",
                                "http://172.19.10.207:8082", "http://172.19.10.207:4200", "http://172.19.10.207:8080",
                                "http://172.19.10.207:80", "http://localhost:80"
                                )
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
    }
//    @Bean
//    CommandLineRunner run (UserService iUserService , RoleRepository iRoleRepository , UserRepository iUserRepository , PasswordEncoder passwordEncoder, TrophyRepository trophyRepository, TrophyService trophyService, SocialNetworkRepository socialNetworkRepository, TrophyTypeRepository trophyTypeRepository)
//    {return  args ->
//    {
//        iUserService.saveRole(new Role("ADMIN"));
//        iUserService.saveRole(new Role("Велоспортсмен"));
//        iUserService.saveRole(new Role("Велолюбитель"));
//
//        iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("1111"), new HashSet<>()));
//
//
////        Trophy trophy = new Trophy();
////        trophy.setTrophyType(trophyTypeRepository.findById(6L).orElse(new TrophyType()));
////        trophyService.addTrophy(trophy);
//
//        Role role = iRoleRepository.findRoleByName("ADMIN");
//        User user = iUserRepository.findUserByEmail("admin@gmail.com").orElse(null);
//        user.getRoles().add(role);
//        iUserService.saverUser(user);
//
//
//        Map<String, String> socialNetwork = new HashMap<>();
//        socialNetwork.put("viber", "+375251111111");
//        socialNetwork.put("vk", "id334414521");
//
//        User test = iUserService.registerAuto(new RegisterDto("Имя", "Фамилия", "+375251111111", "test@mail.ru", "111111", "111111", true, new Date(), "male", null, socialNetwork));
//        System.out.println(test.getId());
//        Set<SocialNetwork> socialNetworks = new HashSet<>();
//        socialNetwork.forEach((name, link) ->{
//            socialNetworks.add(new SocialNetwork(new SocialNetworkDTO(name, link, test)));
//        });
//        socialNetworkRepository.saveAll(socialNetworks);
//    };
//    }

}
