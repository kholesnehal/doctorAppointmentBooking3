package com.perennial.doctorappointmentbooking.entity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long appointmentId;
    @Temporal(TemporalType.DATE)
    @Pattern(regexp = "yyyy-MM-dd")
    private Date appointmentDate;
    private LocalDateTime appointmentStartTime;
    private LocalDateTime appointmentEndTime;
    @NotBlank
    private String appointmentStatus;
    @NotNull
    private long doctorId;
    @NotNull
    private long patientId;
    @OneToOne
    private Payment payment;



}
