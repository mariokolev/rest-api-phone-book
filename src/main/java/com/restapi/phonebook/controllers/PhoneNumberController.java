package com.restapi.phonebook.controllers;

import com.restapi.phonebook.entities.PhoneNumber;
import com.restapi.phonebook.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/phonenumbers")
public class PhoneNumberController {

    @Autowired
    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping(path = "{phoneNumber}")
    public PhoneNumber getPhoneNumberByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        return phoneNumberService.getPhoneNumberByPhoneNumber(phoneNumber);
    }

    @PostMapping
    public void addPhoneNumber(@RequestBody PhoneNumber phoneNumber){
        phoneNumberService.addPhoneNumber(phoneNumber);
    }

    @GetMapping(path = {"person/id"})
    public List<PhoneNumber> getPhoneNumbersByPersonId(@RequestParam("personId") Long personId){
        return phoneNumberService.getPhoneNumbersByPersonId(personId);
    }

    @GetMapping(path = {"city/id"})
    public List<PhoneNumber> getPhoneNumbersFromCityById(@RequestParam("cityId") Long cityId){
        return phoneNumberService.getPhoneNumbersFromCityById(cityId);
    }

    @GetMapping(path = {"phonetype/id"})
    public List<PhoneNumber> getPhoneNumbersByPhoneTypeId(@RequestParam("phoneTypeId") Long phoneTypeId){
        return phoneNumberService.getPhoneNumbersByPhoneTypeId(phoneTypeId);
    }

    @DeleteMapping(path = "{phoneNumberId}")
    public void deletePhoneNumber(@PathVariable("phoneNumberId") Long phoneNumberId){
        try{
            phoneNumberService.deletePhoneNumber(phoneNumberId);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }
}
