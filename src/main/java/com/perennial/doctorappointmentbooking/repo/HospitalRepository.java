package com.perennial.doctorappointmentbooking.repo;
import com.perennial.doctorappointmentbooking.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
