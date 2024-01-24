package by.itminsk.cyclingclubbackend.r_city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findCityByName(String name);
}
