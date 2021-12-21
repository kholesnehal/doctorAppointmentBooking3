package com.perennial.doctorappointmentbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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


}
