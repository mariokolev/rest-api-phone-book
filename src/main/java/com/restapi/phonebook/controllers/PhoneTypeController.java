package com.restapi.phonebook.controllers;

import com.restapi.phonebook.entities.PhoneType;
import com.restapi.phonebook.services.PhoneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

        try{
            return phoneTypeService.getPhoneTypeById(phoneTypeId);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, " phone type width id: " + phoneTypeId + " doesn't exist!", e);
        }
    }

    @PostMapping
    public void addPhoneType(@RequestBody PhoneType phoneType){

        try{
            phoneTypeService.addPhoneType(phoneType);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Phone type with parameters {" + phoneType.getPhoneType() + "} already exists!!", e);
        }
    }

    @PutMapping(path = "{phoneTypeId}")
    public void updatePhoneType(
            @RequestParam(required = false) Long phoneTypeId,
            @RequestParam(required = false) String type){

        try{
            phoneTypeService.updatePhoneType(phoneTypeId, type);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Phone type with id: " + phoneTypeId + " doesn't exist!", e);
        }
    }
}
