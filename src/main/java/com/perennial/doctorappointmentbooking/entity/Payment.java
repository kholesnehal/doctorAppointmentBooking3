package com.perennial.doctorappointmentbooking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer payment_id;
    private String payment_mode;
    private double ammount;
    private String payment_date;
    private String payment_time;
    private Integer appointment_id;

}
