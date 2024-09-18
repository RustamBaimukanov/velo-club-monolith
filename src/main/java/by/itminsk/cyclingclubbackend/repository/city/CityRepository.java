package by.itminsk.cyclingclubbackend.repository.city;

import by.itminsk.cyclingclubbackend.model.city.City;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface CityRepository extends JpaRepository<City, Long> {

    City findCityByName(String name);

}
