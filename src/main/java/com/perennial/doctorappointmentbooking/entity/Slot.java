package com.perennial.doctorappointmentbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Slot {
    @Id
    private long slotId;
    @NotNull
    private Date startTime; // 24hr format.

    @NotNull
    private Date endTime;

    @NotNull
    private long doctorId;

    private boolean isBooked;

    private boolean isBookedBySameUser;
}
