package com.perennial.doctorappointmentbooking.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer doctorId;
    private String licenceNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private long phone;
    private String speciality;
    private Integer experience;
    private String education;
    private String status;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorId_fk",referencedColumnName = "doctorId")
    List<Appointment> appointmentList=new ArrayList<>();

@OneToMany(targetEntity = Patient.class,cascade = CascadeType.ALL)
@JoinColumn(name = "doctor_Id",referencedColumnName = "doctorId")
List<Patient> patients=new ArrayList<>();

    @OneToMany(targetEntity = Hospital.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctorId")
    List<Hospital> hospitals=new ArrayList<>();

}
