package com.perennial.doctorappointmentbooking.controller;
import com.perennial.doctorappointmentbooking.dto.Request;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepo;
import com.perennial.doctorappointmentbooking.repo.DoctorRepo;
import com.perennial.doctorappointmentbooking.repo.PatientRepo;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
import com.perennial.doctorappointmentbooking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;

    @PostMapping("/upload-appointment")
    @ResponseBody
    public ResponseEntity<ResponseMessage> uploadExcelFile(@RequestParam("file") MultipartFile file)
    {
        String message = "";

        if (AppointmentHelper.checkExcelFormatOfAppointment(file))
        {
            try {
                appointmentService.save(file);
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


    @PostMapping("/add-appointment")
    private Doctor placeDoctor(@RequestBody Request request)
    {
        return doctorRepo.save(request.getDoctor());
    }
    @PostMapping("/add-patient")
    private Patient placePatient(@RequestBody Request request)
    {
        return patientRepo.save(request.getPatient());
    }


@GetMapping("/appointments-fordoctor/{status}")

public List<Appointment> findAllAppointmentsforDoctor(@PathVariable String status) {
    return appointmentService.findAllAppointmentsforDoctor(status);
}


@GetMapping("/getappointmentbyid/{id}")
public  Appointment getAppointmentById(@PathVariable int appointmentId)
{
    return appointmentRepo.findByAppointmentId(appointmentId);
}


    @PutMapping("/update-appointment/{id}")
 public Appointment updateAppointment(@RequestBody Appointment appointment,@PathVariable int appointmnetId)
    {
        return this.appointmentService.updateAppointment(appointment,appointmnetId);
    }
    @DeleteMapping("/delete-appointment/{id}")
    public String deleteAppointment(@PathVariable int appointmentId)
    {
       return this.appointmentService.deleteAppointment(appointmentId);
    }

    }

