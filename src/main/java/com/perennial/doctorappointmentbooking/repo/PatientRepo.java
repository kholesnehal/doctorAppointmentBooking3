package com.perennial.doctorappointmentbooking.repo;

import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {

}
