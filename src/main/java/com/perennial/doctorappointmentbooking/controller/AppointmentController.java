package com.perennial.doctorappointmentbooking.controller;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.perennial.doctorappointmentbooking.dto.Request;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepo;
import com.perennial.doctorappointmentbooking.repo.DoctorRepo;
import com.perennial.doctorappointmentbooking.repo.PatientRepo;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
import com.perennial.doctorappointmentbooking.service.AppointmentInterface;
import com.perennial.doctorappointmentbooking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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


    @RequestMapping(path = "/add-doctor",method = RequestMethod.POST)
    private Doctor placeDoctor(@RequestBody Request request)
    {
        return doctorRepo.save(request.getDoctor());
    }
    @RequestMapping(path = "/add-patient",method = RequestMethod.POST)
    private Patient placePatient(@RequestBody Request request)
    {
        return patientRepo.save(request.getPatient());
    }
@RequestMapping(path="/add-appointment",method = RequestMethod.POST)
private Appointment addAppointment(Appointment appointment )
{
    return appointmentService.addAppointment(appointment);
}
@GetMapping("/appointment-for-doctor")
public List<Appointment> findAllAppointmentsForDoctor(@RequestParam long doctorId) {
    return appointmentService.findAllAppointmentsforDoctor(doctorId);

}
@GetMapping("/appointment-for-patient")
public List<Appointment>findAllAppointmentsForPatient(@RequestParam long patientId)
{
    return appointmentService.findAllAppointmentsforPatient(patientId);
}

@RequestMapping(path = "/{appointmentId}", method = RequestMethod.GET)
public  Appointment getAppointmentById(@PathVariable Long appointmentId)
{
    return appointmentRepo.findByAppointmentId(appointmentId);
}

@PutMapping("/update/{appointmentId}")
 public Appointment updateAppointment(@RequestBody Appointment appointment)
    {
        return appointmentService.updateAppointment(appointment);
    }

@RequestMapping(path = "/{appointmentId}", method = RequestMethod.DELETE)
void deleteAppointment(@PathVariable Long appointmentId) {
    appointmentService.deleteAppointment(appointmentId);
}

    @RequestMapping(path = "/{appointmentId}", method = RequestMethod.PATCH)
    public Appointment updateStatus(@PathVariable Long appointmentId, @RequestBody Appointment appointment) {
        return appointmentService.updateStatus(appointmentId, appointment);
    }

@GetMapping("/byid")
public Optional<Appointment> getAppointmentById(@RequestParam("appointmentId") long appointmentId) throws IOException, InvalidFormatException {
    Optional<Appointment> appointment=appointmentRepo.findById(appointmentId);
    return appointment;
}

}




