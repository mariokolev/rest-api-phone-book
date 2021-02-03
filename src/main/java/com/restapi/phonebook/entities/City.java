package com.restapi.phonebook.entities;

import javax.persistence.*;

@Entity(name = "City")
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "region")
    private String region;

    public City() {}

    public City(Long id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public City(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}