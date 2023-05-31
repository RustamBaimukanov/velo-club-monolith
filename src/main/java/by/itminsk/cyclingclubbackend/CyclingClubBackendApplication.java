package by.itminsk.cyclingclubbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
        (exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class CyclingClubBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyclingClubBackendApplication.class, args);
    }

}
