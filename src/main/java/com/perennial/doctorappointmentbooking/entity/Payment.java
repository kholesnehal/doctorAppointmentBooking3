package com.perennial.doctorappointmentbooking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    private String paymentMode;
    private double ammount;
    private String paymentDate;
    private String paymentTime;
//    private Integer appointmentId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


//    @OneToOne
//    private Appointment appointment;

}
