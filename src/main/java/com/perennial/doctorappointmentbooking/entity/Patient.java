package com.perennial.doctorappointmentbooking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer patient_id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String gender;
    private long phone;
    private int age;
    private String reason;
}
