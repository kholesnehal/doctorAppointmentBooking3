package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.helper.PatientHelper;
import com.perennial.doctorappointmentbooking.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public void save(MultipartFile file) {
        try {
            List<Patient> patients = PatientHelper.convertExcelToListOfPatient(file.getInputStream());
            try {
                patients.forEach(l -> patientRepository.save(l));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }


}
