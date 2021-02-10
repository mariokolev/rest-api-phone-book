package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.email = ?1")
    Optional<Person> findPersonByEmail(String email);

    @Query("SELECT p FROM Person p WHERE p.city.name = :cityName and p.city.region = :cityRegion")
    List<Person> findAllFromCity(@Param("cityName")String cityName,
                                 @Param("cityRegion") String cityRegion);
}
