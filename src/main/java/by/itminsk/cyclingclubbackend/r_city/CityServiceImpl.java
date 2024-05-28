package by.itminsk.cyclingclubbackend.r_city;

import by.itminsk.cyclingclubbackend.util.exception_handler.ObjectNotFound;
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
        if (!cityRepository.existsById(id)) throw new ObjectNotFound("Регион не найден.");

    }
}
