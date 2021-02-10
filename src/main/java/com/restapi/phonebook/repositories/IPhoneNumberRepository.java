package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}
