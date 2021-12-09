package com.perennial.doctorappointmentbooking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hospital_id;
    private String hospital_name;
    private String hospital_address;
    private long phone;
}
