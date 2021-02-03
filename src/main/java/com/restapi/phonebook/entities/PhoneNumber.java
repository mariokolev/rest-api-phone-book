package com.restapi.phonebook.entities;

import javax.persistence.*;

@Entity(name = "PhoneNumber")
@Table(name = "phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "phone_type_id")
    private PhoneType phoneType;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    public PhoneNumber() {}

    public PhoneNumber(PhoneType phoneType, String phoneNumber) {
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
