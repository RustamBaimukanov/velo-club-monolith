package by.itminsk.cyclingclubbackend.r_city;

import java.util.List;

public interface CityService {

    void generateCities();

    List<City> getCities();

    City getCity(String name);

    City getCityById(Long id);

    void cityExistenceValidator(Long id);


}
