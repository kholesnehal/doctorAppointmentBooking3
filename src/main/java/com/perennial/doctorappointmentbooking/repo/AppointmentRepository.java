package com.perennial.doctorappointmentbooking.repo;

import com.perennial.doctorappointmentbooking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByAppointmentId(long appointmentId);
//    List<Appointment> findByMonthAndDay(Integer month, Integer day);
}
