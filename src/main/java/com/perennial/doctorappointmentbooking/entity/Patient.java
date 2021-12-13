package com.perennial.doctorappointmentbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer patientId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String gender;
    private long phone;
    private int age;
    private String reason;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "patient_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

//    @OneToMany(mappedBy = "hospital_id", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Hospital> hospitals;



    }
