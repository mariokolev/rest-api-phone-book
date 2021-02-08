package com.restapi.phonebook.services;

import com.restapi.phonebook.entities.PhoneType;
import com.restapi.phonebook.repositories.IPhoneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PhoneTypeService {

    @Autowired
    private final IPhoneTypeRepository phoneTypeRepository;

    public PhoneTypeService(IPhoneTypeRepository phoneTypeRepository) {
        this.phoneTypeRepository = phoneTypeRepository;
    }

    public void addPhoneType(PhoneType phoneType){
        Optional<PhoneType> phoneTypeOptional = phoneTypeRepository
                .findPhoneTypeByType(phoneType.getPhoneType());

        if(phoneTypeOptional.isPresent()){
            throw new IllegalStateException("phone type already exists!!");
        }

        phoneTypeRepository.save(phoneType);
    }

    public List<PhoneType> getPhoneTypes(){
       return phoneTypeRepository.findAll();
    }

    public PhoneType getPhoneTypeById(Long phoneTypeId){
        return phoneTypeRepository.findById(phoneTypeId).orElseThrow(() -> new IllegalStateException(
                "phone type with id " + phoneTypeId + " does not exists"
        ));
    }

    @Transactional
    public void updatePhoneType(Long phoneTypeId, String type){

        PhoneType phoneType = phoneTypeRepository.findById(phoneTypeId)
                .orElseThrow(() -> new IllegalStateException(
                        "phone type with id: " + phoneTypeId + " doesn't exist!!"
                ));

        if(type != null &&
            type.length() > 0 &&
            !Objects.equals(phoneType.getPhoneType(),type)){
                phoneType.setPhoneType(type);
        }
    }
}
