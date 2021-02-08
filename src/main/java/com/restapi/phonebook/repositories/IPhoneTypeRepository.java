package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IPhoneTypeRepository extends JpaRepository<PhoneType, Long> {

    @Query("SELECT p FROM PhoneType p WHERE p.phoneType = ?1")
    Optional<PhoneType> findPhoneTypeByType(String phoneType);
}
