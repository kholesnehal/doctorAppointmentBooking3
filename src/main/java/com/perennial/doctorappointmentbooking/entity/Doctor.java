package com.perennial.doctorappointmentbooking.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Doctor {
    @OneToMany(targetEntity = Appointment.class, cascade = CascadeType.ALL)
    List<Appointment> appointmentList;
    @OneToMany(targetEntity = Patient.class, cascade = CascadeType.ALL)
    List<Patient> patients;
    @OneToMany(targetEntity = Hospital.class, cascade = CascadeType.ALL)
    List<Hospital> hospitals;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long doctorId;
    @NotEmpty
    private String licenceNumber;
    @NotBlank
    @Size(max = 100)
    private String firstName;
    @Size(max = 100)
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @Size(max = 100)
    private String address;
    @Pattern(regexp = "(0|91)?[7-9][0-9]{9}")
    private long phone;
    @NotBlank
    private String speciality;
    private Integer experience;
    @NotBlank
    private String education;
    private String status;


//    public Doctor(List<Appointment> appointmentList, List<Patient> patients, List<Hospital> hospitals, long doctorId, String licenceNumber, String firstName, String lastName, String email, String address, long phone, String speciality, Integer experience, String education, String status) {
//        this.appointmentList = appointmentList;
//        this.patients = patients;
//        this.hospitals = hospitals;
//        this.doctorId = doctorId;
//        this.licenceNumber = licenceNumber;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.address = address;
//        this.phone = phone;
//        this.speciality = speciality;
//        this.experience = experience;
//        this.education = education;
//        this.status = status;
//    }


}
