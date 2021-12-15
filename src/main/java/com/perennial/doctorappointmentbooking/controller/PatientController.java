package com.perennial.doctorappointmentbooking.controller;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.helper.HospitalHelper;
import com.perennial.doctorappointmentbooking.repo.PatientRepo;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
import com.perennial.doctorappointmentbooking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    private PatientRepo patientRepo;
    @PostMapping("/upload-patient")
    @ResponseBody
    public ResponseEntity<ResponseMessage> uploadExcelFileOfPatient(@RequestParam("file") MultipartFile file)
    {
        String message = "";

        if (HospitalHelper.checkExcelFormatOfHospital(file))
        {
            try {
                patientService.save(file);
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


    @PostMapping("/add-patient")
    @ResponseBody
    public Patient addPatient(@RequestBody Patient patient)
    {
        return patientService.addPatient(patient);
    }



    @GetMapping("/byid")
    public Optional<Patient> getPatientById(@RequestParam("patientId") long patientId) throws IOException, InvalidFormatException {
        Optional<Patient> patient=patientRepo.findById(patientId);
        return patient;
    }


}
