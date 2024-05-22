package by.itminsk.cyclingclubbackend.r_city;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface CityRepository extends JpaRepository<City, Long> {

    City findCityByName(String name);
}
