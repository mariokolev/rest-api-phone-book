package com.restapi.phonebook.controllers;

import com.restapi.phonebook.services.PhoneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/phonetypes")
public class PhoneTypeController {

    @Autowired
    private final PhoneTypeService phoneTypeService;

    public PhoneTypeController(PhoneTypeService phoneTypeService) {
        this.phoneTypeService = phoneTypeService;
    }
}
