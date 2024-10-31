package com.work.veloclub.service.city;

import com.work.veloclub.model.city.City;

import java.util.List;

public interface CityService {

    void generateCities();

    List<City> getCities();

    City getCity(String name);

    City getCityById(Long id);

    void cityExistenceValidator(Long id);


}
