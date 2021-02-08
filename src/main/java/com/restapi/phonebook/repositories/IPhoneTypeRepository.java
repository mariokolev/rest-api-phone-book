package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneTypeRepository extends JpaRepository<PhoneType, Long> {
}
