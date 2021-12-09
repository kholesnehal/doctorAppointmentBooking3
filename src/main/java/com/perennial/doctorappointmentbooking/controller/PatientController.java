package com.perennial.doctorappointmentbooking.controller;

import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.helper.HospitalHelper;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
import com.perennial.doctorappointmentbooking.service.HospitalService;
import com.perennial.doctorappointmentbooking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/uploadpatient")
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
    @PostMapping("/addpatient")
    @ResponseBody
    public Patient addPatient(@RequestBody Patient patient)
    {
        return patientService.addPatient(patient);
    }

    @GetMapping("/patient")
    public List<Patient> getAllPatient() {
        return this.patientService.getAllPatient();
    }
}
