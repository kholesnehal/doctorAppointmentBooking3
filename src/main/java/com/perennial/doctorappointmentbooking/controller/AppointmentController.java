package com.perennial.doctorappointmentbooking.controller;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.perennial.doctorappointmentbooking.dto.Request;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepository;
import com.perennial.doctorappointmentbooking.repo.DoctorRepository;
import com.perennial.doctorappointmentbooking.repo.PatientRepository;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
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
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DoctorRepository doctorRepo;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<ResponseMessage> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (AppointmentHelper.checkExcelFormatOfAppointment(file)) {
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


    @PostMapping("/appointments/doctors")
    private Doctor placeDoctor(@RequestBody Request request) {
        return doctorRepo.save(request.getDoctor());
    }


    @PostMapping("/appointments/patients")
    private Patient placePatient(@RequestBody Request request) {
        return patientRepository.save(request.getPatient());
    }


    @PostMapping("/appointments")
    private Appointment addAppointment(Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }


    @GetMapping("/doctors/{doctorId}/appointments")
    public List<Appointment> findAllAppointmentsForDoctor(@PathVariable("doctorId") long doctorId) {
        return appointmentService.findAllAppointmentsforDoctor(doctorId);

    }


    @GetMapping("/patients/{patientId}/appointments")
    public List<Appointment> findAllAppointmentsForPatient(@PathVariable("patientId") long patientId) {
        return appointmentService.findAllAppointmentsforPatient(patientId);
    }


    @PutMapping("/{appointmentId}")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
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


    @GetMapping("/{appointmentId}")
    public Optional<Appointment> getAppointmentById(@PathVariable("appointmentId") long appointmentId) throws IOException, InvalidFormatException {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        return appointment;
    }


    @PostMapping( "/checkappointment")
    private boolean isAppointmentAlreadyExist(@RequestBody Appointment appointment) {
        if (appointmentService.isAppointmentBooked(appointment))
            return true;
        else
            return false;
    }


    }

