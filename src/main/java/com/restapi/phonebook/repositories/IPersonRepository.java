package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.egn = ?1")
    Optional<Person> findPersonByEGN(String egn);
}
