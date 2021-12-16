package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.helper.HospitalHelper;
import com.perennial.doctorappointmentbooking.repo.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    public void save(MultipartFile file) {
        try {
            List<Hospital> hospitals = HospitalHelper.convertExcelToListOfHospital(file.getInputStream());

            try {
                hospitals.forEach(l -> hospitalRepository.save(l));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public Hospital addhospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }


}
