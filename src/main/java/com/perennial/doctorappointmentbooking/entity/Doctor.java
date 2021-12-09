package com.perennial.doctorappointmentbooking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer doctor_id;
    private String licence_number;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private long phone;
    private String speciality;
    private Integer experience;
    private String education;
    private Integer appointment_id;
    private String status;
    private Integer hospital_id;
}
