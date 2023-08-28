package by.itminsk.cyclingclubbackend.service.city;

import by.itminsk.cyclingclubbackend.model.user.City;
import by.itminsk.cyclingclubbackend.repository.CityRepository;
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
}
