package com.restapi.phonebook.repositories;

import com.restapi.phonebook.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.name = ?1 AND c.region = ?2")
    public Optional<City> getCityByName(String name, String region);
}
