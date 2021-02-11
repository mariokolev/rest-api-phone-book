package com.restapi.phonebook.services;

import com.restapi.phonebook.entities.PhoneNumber;
import com.restapi.phonebook.repositories.IPhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneNumberService {

    @Autowired
    private final IPhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(IPhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber){
        return phoneNumberRepository.findByPhoneNumber(phoneNumber).get();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        Optional<PhoneNumber> phoneNumberOptional = phoneNumberRepository
               .findByPhoneNumber(phoneNumber.getPhoneNumber());

        if(phoneNumberOptional.isPresent()){
            throw new IllegalStateException("phone number:" + phoneNumber.getPhoneNumber() + " exists!!");
        }

        phoneNumberRepository.save(phoneNumber);
    }
}
