package com.perennial.doctorappointmentbooking.repo;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

    Appointment findByAppointmentId(int appointment_id);

}
