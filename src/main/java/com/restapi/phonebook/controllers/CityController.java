package com.restapi.phonebook.controllers;

import com.restapi.phonebook.entities.City;
import com.restapi.phonebook.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getCities(){
        return cityService.getCities();
    }

    @GetMapping(path = "{cityId}")
    public City getCityById(@PathVariable("cityId") Long cityId){

        try{
            return cityService.getCityById(cityId);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "City with id: " + cityId + " not found", e);
        }
    }

    @PostMapping
    public void addCity(@RequestBody City city){
        try{
            cityService.addCity(city);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "City with parameters {" + city.getName() +
                    ", " + city.getRegion() + "} exists", e);
        }
    }

    @PutMapping(path = "{cityId}")
    public void updateCity(@PathVariable("cityId") Long cityId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String region){

        try {
            cityService.updateCity(cityId, name, region);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "City with id: " + cityId + " doesn't exist!", e);
        }
    }
}
