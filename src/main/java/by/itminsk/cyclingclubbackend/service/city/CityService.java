package by.itminsk.cyclingclubbackend.service.city;

import by.itminsk.cyclingclubbackend.model.city.City;

import java.util.List;

public interface CityService {

    void generateCities();

    List<City> getCities();

    City getCity(String name);

    City getCityById(Long id);

    void cityExistenceValidator(Long id);


}
