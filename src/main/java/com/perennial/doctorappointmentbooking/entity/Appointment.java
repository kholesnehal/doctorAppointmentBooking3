package com.perennial.doctorappointmentbooking.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long appointmentId;
    //    @Temporal(TemporalType.DATE)
    private String appointmentDate;
    private LocalDateTime appointmentStartTime;
    private LocalDateTime appointmentEndTime;
    @NotBlank
    private String appointmentStatus;
    @NotBlank
    private long doctorId;
    @NotBlank
    private long patientId;
    @OneToOne
    private Payment payment;


}
