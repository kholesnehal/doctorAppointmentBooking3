package com.perennial.doctorappointmentbooking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private long phone;


}
