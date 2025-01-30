package com.work.veloclub.service.city;

import com.work.veloclub.model.city.City;

import java.util.List;

public interface CityService {

    void generateCities();

    List<City> getCities();

    City getCity(String name);

    City getCityById(Long id);

    /**
     * Проверяет существование города по введенному пользователем id
     * Если проверка не проходит, инициирует ответ с кодом 400
     * @param id - id города
     */
    void cityExistenceValidator(Long id);


}
