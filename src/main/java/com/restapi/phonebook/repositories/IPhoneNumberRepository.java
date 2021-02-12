package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    @Query("SELECT p FROM PhoneNumber p WHERE p.phoneNumber = :phoneNumber")
    Optional<PhoneNumber> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT p FROM PhoneNumber p WHERE p.person.id = :personId")
    List<PhoneNumber> getPhoneNumbersByPersonId(@Param("personId") Long personId);

    @Query("SELECT p FROM PhoneNumber p WHERE p.person.city.id = :cityId")
    List<PhoneNumber> getPhoneNumbersFromCityById(@Param("cityId")Long cityId);

    @Query("SELECT p FROM PhoneNumber p WHERE p.phoneType.id = :phoneTypeId")
    List<PhoneNumber> getPhoneNumbersByPhoneTypeId(@Param("phoneTypeId")Long phoneTypeId);
}
