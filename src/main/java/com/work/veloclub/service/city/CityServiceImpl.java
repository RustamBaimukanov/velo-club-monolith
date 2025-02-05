package com.work.veloclub.service.city;

import com.work.veloclub.model.city.City;
import com.work.veloclub.repository.city.CityRepository;
import com.work.veloclub.util.exception_handler.ObjectNotFound;
import com.work.veloclub.util.exception_handler.error_message.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void generateCities() {

    }

    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCity(String name) {
        return cityRepository.findCityByName(name);
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(new City(1L,"Минск"));
    }

    @Override
    public void cityExistenceValidator(Long id) {
        if (id != null && !cityRepository.existsById(id)) throw new ObjectNotFound(ErrorMessages.CityErrors.NOT_FOUND);

    }
}
