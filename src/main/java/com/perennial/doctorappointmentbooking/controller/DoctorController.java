package com.perennial.doctorappointmentbooking.controller;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.perennial.doctorappointmentbooking.dto.Request;
import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.helper.DoctorHelper;
import com.perennial.doctorappointmentbooking.repo.DoctorRepo;
import com.perennial.doctorappointmentbooking.repo.PatientRepo;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
import com.perennial.doctorappointmentbooking.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    PatientRepo patientRepo;
    @PostMapping("/upload-doctor")
    @ResponseBody
    public ResponseEntity<ResponseMessage> uploadExcelFile(@RequestParam("file") MultipartFile file)
    {
        String message = "";

        if (DoctorHelper.checkExcelFormatOfDoctor(file))
        {
            try {
                doctorService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping("/add-doctor")
    private Doctor placeDoctor(@RequestBody Request request)
    {
        return doctorRepo.save(request.getDoctor());
    }


    @GetMapping("/all-doctor")
    public List<Doctor> getAllDoctor() {
        return doctorRepo.findAll();
    }
    @GetMapping("/byid")
    public Optional<Doctor> getDoctorById(@RequestParam("doctorId") long doctorId) {
        Optional<Doctor> doctor=doctorRepo.findById(doctorId);
        return doctor;
    }

@RequestMapping(path = "get-doctors",method = RequestMethod.GET)
    public List<Doctor> getAvailableDoctors(@RequestParam String status) {
       List<Doctor> availableDoctors = doctorService.getAllDoctors(status);
        return availableDoctors;
    }
}
