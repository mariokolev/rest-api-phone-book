package com.restapi.phonebook.services;

import com.restapi.phonebook.entities.City;
import com.restapi.phonebook.repositories.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CityService {

    private final ICityRepository cityRepository;

    @Autowired
    public CityService(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities(){
        return cityRepository.findAll();
    }

    public City getCityById(Long cityId) {

        return cityRepository.findById(cityId).orElseThrow(() -> new IllegalStateException(
                "city with id " + cityId + " does not exists"
        ));
    }

    public void addCity(City city){

        Optional<City> cityOptional = cityRepository
                .getCityByName(city.getName(), city.getRegion());

        if(cityOptional.isPresent()){
            throw new IllegalMonitorStateException("city already exists!!");
        }

        cityRepository.save(city);
    }

    @Transactional
    public void updateCity(Long cityId, String name, String region) {

        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalStateException(
                        "city with id: " + cityId + " doesn't exist!!"
                ));

        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(city.getName(), name)){
            city.setName(name);
        }

        if(region != null &&
                region.length() > 0 &&
                !Objects.equals(city.getRegion(), region)){
            city.setRegion(region);
        }
    }
}
