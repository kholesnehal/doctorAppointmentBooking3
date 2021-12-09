package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.helper.HospitalHelper;
import com.perennial.doctorappointmentbooking.helper.PatientHelper;
import com.perennial.doctorappointmentbooking.repo.HospitalRepo;
import com.perennial.doctorappointmentbooking.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
@Service
public class PatientService {
    @Autowired
    PatientRepo patientRepo;

    public void save(MultipartFile file) {
        try {
            List<Patient> patients= PatientHelper.convertExcelToListOfPatient(file.getInputStream());
            try {
                patients.forEach(l -> patientRepo.save(l));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch ( IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Patient> getAllPatient()
    {
        return patientRepo.findAll();
    }
    public Patient addPatient(Patient patient)
    {
        return patientRepo.save(patient);
    }


}
