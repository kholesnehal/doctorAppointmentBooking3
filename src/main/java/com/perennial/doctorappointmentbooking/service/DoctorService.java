package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.helper.DoctorHelper;
import com.perennial.doctorappointmentbooking.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public void save(MultipartFile file) {
        try {
            List<Doctor> doctors = DoctorHelper.convertExcelToListOfDoctor(file.getInputStream());

            doctors.forEach(s -> System.out.println(s.toString()));

            try {
                doctors.forEach(l -> doctorRepository.save(l));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public Doctor adddoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors(String status) {
        List<Doctor> d = doctorRepository.findAll().stream()
                .filter(doctor -> doctor.getStatus().equals(status))
                .collect(Collectors.toList());
        return d;

    }

    public void deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);

    }

    public boolean isDoctorAlreadyExist(Doctor doctor) {
        List<Doctor> doctorList = doctorRepository.findAll();
        for (Doctor d : doctorList) {
            if (d.getLicenceNumber() != null && d.getFirstName().equals(doctor.getFirstName())
                    && d.getLastName().equals(doctor.getLastName())
                    && d.getAddress().equals(doctor.getAddress()) && d.getEmail().equals(doctor.getEmail())
                    && d.getStatus().equals(doctor.getStatus()) && d.getEducation().equals(doctor.getEducation())
                    && d.getPhone() == doctor.getPhone() && d.getSpeciality().equals(doctor.getSpeciality())) {
                return true;
            }
        }
        return false;
    }
}


