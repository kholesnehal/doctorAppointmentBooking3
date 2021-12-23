package com.perennial.doctorappointmentbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long paymentId;
    private String paymentMode;
    @Min(500)
    private double ammount;
    @Temporal(TemporalType.DATE)
    private Date paymentDate = new Date(System.currentTimeMillis());
    private String paymentTime;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;



}
