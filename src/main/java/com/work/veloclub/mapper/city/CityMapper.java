package com.work.veloclub.mapper.city;

import com.work.veloclub.model.city.City;
import com.work.veloclub.model.city.CityDTO;

public class CityMapper {

    public static CityDTO mapToCityDto(City city){
        if (city == null){
            return null;
        }

        return new CityDTO(city.getId(), city.getName());
    }
}
