package com.perennial.doctorappointmentbooking.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long hospitalId;
    @NotEmpty
    private String hospitalName;
    @NotEmpty
    private String hospitalAddress;
    @Pattern(regexp = "(0|91)?[7-9][0-9]{9}")
    private long phone;


}
