package com.restapi.phonebook.entities;

import javax.persistence.*;

@Entity(name = "PhoneType")
@Table(name = "phone_types")
public class PhoneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_type", unique = true)
    private String phoneType;

    public PhoneType() {}

    public PhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
}
