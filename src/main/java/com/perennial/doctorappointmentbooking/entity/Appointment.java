package com.perennial.doctorappointmentbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;


@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer appointment_id;
    private String appointment_date;
    private String appointment_time;
    private int payment_id;
    private String appointment_status;
    private int doctor_id;
    private int patient_id;
    private int hospital_id;



}
