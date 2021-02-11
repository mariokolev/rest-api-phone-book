package com.restapi.phonebook.controllers;

import com.restapi.phonebook.entities.PhoneNumber;
import com.restapi.phonebook.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
