package com.perennial.doctorappointmentbooking.repo;
import ch.qos.logback.core.status.Status;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    Appointment findByAppointmentId(long appointmentId);

//    List<Appointment> findByPatientId(long patientId);

//    List<Appointment> findAllAppointmentBetweenDate(LocalDate startDate, LocalDate endDate);


//List<Appointment> findAllByPatientIdAndSTATUS(String userId, Status status);
    }
