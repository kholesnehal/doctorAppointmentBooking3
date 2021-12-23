package com.perennial.doctorappointmentbooking;

import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AppointmentRepositoryTests {
    private AppointmentRepository appointmentRepository;

//    JUnit test for saveappointment
    @Test
    public void saveAppointmentTest()
    {
        Appointment appointment=Appointment.builder()
                .appointmentDate("2022-01-02")
                .appointmentStatus("done")
                .appointmentStartTime(04:00:00).build();

        appointmentRepository.save(appointment);
        Assertions.assertThat(appointment.getAppointmentId().isgreaterThan(0));
    }
}
