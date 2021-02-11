package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    @Query("SELECT p FROM PhoneNumber p WHERE p.phoneNumber = :phoneNumber")
    Optional<PhoneNumber> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
