package com.perennial.doctorappointmentbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer appointmentId;
    private String appointmentDate;
    private String appointmentTime;
//    private int paymentId;
    private String appointmentStatus;
//    private int doctorId;
//    private int patientId;
//    private int hospitalId;
    @OneToOne
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient_id;



    public Appointment(){}

}
