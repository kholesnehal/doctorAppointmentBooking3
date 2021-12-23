package com.perennial.doctorappointmentbooking.repo;

import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> getAllByDoctorId(Long currentDoctorId);
}
