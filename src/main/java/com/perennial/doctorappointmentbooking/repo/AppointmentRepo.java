package com.perennial.doctorappointmentbooking.repo;
import ch.qos.logback.core.status.Status;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    Appointment findByAppointmentId(long appointmentId);





}
