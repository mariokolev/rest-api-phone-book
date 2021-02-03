package com.restapi.phonebook.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Person")
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "egn", unique = true)
    private String egn;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private List<PhoneNumber> phoneNumbers;

    public Person() {}

    public Person(String name, String middleName, String lastName, String egn) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.egn = egn;
    }

    public Person(String name, String middleName, String lastName, String egn, City city, List<PhoneNumber> phoneNumbers) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.egn = egn;
        this.city = city;
        this.phoneNumbers = phoneNumbers;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
