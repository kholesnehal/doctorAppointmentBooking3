package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.helper.DoctorHelper;
import com.perennial.doctorappointmentbooking.helper.HospitalHelper;
import com.perennial.doctorappointmentbooking.repo.DoctorRepo;
import com.perennial.doctorappointmentbooking.repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class HospitalService {
    @Autowired
    HospitalRepo hospitalRepo;

    public void save(MultipartFile file) {
        try {
            List<Hospital> hospitals= HospitalHelper.convertExcelToListOfHospital(file.getInputStream());
            hospitals.forEach(s-> System.out.println(s.toString()));

            try {
               hospitals.forEach(l -> hospitalRepo.save(l));
                }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch ( IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Hospital> getAllHospital()
    {
        return hospitalRepo.findAll();
    }
    public Hospital addhospital(Hospital hospital)
    {
        return hospitalRepo.save(hospital);
    }


}
