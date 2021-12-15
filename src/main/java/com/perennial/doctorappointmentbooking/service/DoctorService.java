package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.helper.DoctorHelper;
import com.perennial.doctorappointmentbooking.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    public void save(MultipartFile file) {
        try {
            List<Doctor> doctors= DoctorHelper.convertExcelToListOfDoctor(file.getInputStream());

            doctors.forEach(s-> System.out.println(s.toString()));

            try {
                doctors.forEach(l -> doctorRepo.save(l));

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch ( IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public Doctor adddoctor(Doctor doctor)
    {
        return doctorRepo.save(doctor);
    }

    public List<Doctor> getAllDoctors(String status) {
        List<Doctor>d= doctorRepo.findAll().stream()
                .filter(doctor -> doctor.getStatus().equals(status))
                .collect(Collectors.toList());
        return d;

    }


}


