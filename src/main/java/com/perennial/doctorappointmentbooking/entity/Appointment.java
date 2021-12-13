package com.perennial.doctorappointmentbooking.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentStatus;

    @OneToOne
    private Payment payment;




}
