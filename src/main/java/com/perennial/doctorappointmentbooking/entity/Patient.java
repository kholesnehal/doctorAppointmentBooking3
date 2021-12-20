package com.perennial.doctorappointmentbooking.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
    @OneToMany(cascade = CascadeType.ALL)
    List<Appointment> appointmentList;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private long patientId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    private String email;
    private String address;
    private String gender;
    @Pattern(regexp = "(0|91)?[7-9][0-9]{9}")
    private long phone;
    @NotNull
    private int age;
    private String reason;


}
