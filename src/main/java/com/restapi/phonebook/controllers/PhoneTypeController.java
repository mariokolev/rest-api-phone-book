package com.restapi.phonebook.controllers;

import com.restapi.phonebook.entities.PhoneType;
import com.restapi.phonebook.services.PhoneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/phonetypes")
public class PhoneTypeController {

    @Autowired
    private final PhoneTypeService phoneTypeService;

    public PhoneTypeController(PhoneTypeService phoneTypeService) {
        this.phoneTypeService = phoneTypeService;
    }

    @GetMapping
    public List<PhoneType> getPhoneTypes(){
        return phoneTypeService.getPhoneTypes();
    }

    @GetMapping(path = "{phoneTypeId}")
    public PhoneType getPhoneTypeById(@PathVariable("phoneTypeId") Long phoneTypeId){
        return phoneTypeService.getPhoneTypeById(phoneTypeId);
    }

    @PostMapping
    public void addPhoneType(@RequestBody PhoneType phoneType){
        phoneTypeService.addPhoneType(phoneType);
    }

    @PutMapping(path = "{phoneTypeId}")
    public void updatePhoneType(
            @RequestParam(required = false) Long phoneTypeId,
            @RequestParam(required = false) String type){
        phoneTypeService.updatePhoneType(phoneTypeId, type);
    }
}
