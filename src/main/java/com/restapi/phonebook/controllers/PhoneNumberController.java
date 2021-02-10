package com.restapi.phonebook.controllers;

import com.restapi.phonebook.entities.PhoneNumber;
import com.restapi.phonebook.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
