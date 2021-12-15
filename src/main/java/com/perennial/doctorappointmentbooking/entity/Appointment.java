package com.perennial.doctorappointmentbooking.entity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long appointmentId;
    @Temporal(TemporalType.DATE)
    private Date appointmentDate=new Date(System.currentTimeMillis());
    private String appointmentTime;
    private String appointmentStatus;

    @OneToOne
    private Payment payment;




}
