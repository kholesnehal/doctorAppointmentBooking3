package com.perennial.doctorappointmentbooking.controller;
import com.perennial.doctorappointmentbooking.dto.Request;
import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.helper.DoctorHelper;
import com.perennial.doctorappointmentbooking.repo.DoctorRepository;
import com.perennial.doctorappointmentbooking.repo.PatientRepository;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
import com.perennial.doctorappointmentbooking.service.DoctorService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private static final Logger logger = LogManager.getLogger(DoctorController.class);
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<ResponseMessage> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (DoctorHelper.checkExcelFormatOfDoctor(file)) {
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

    @PostMapping("/doctors")
    private Doctor addDoctor(@RequestBody Request request) {
        return doctorRepository.save(request.getDoctor());
    }


    @GetMapping("/getdoctors")
    public List<Doctor> getAllDoctor() {
        logger.info("Doctor list:");
        return doctorService.getAllDoctor();
    }


    @GetMapping("/{id}")
    public Optional<Doctor> getDoctorById(@PathVariable("doctorId") long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        return doctor;
    }


    @GetMapping("/doctors/available")
    public List<Doctor> getAvailableDoctors(@RequestParam String status) {
        List<Doctor> availableDoctors = doctorService.getAllDoctorsByStatus(status);
        return availableDoctors;
    }


    @DeleteMapping("/{id}")
    public void deleteDoctorById(@RequestParam long doctorId) {
        doctorService.deleteDoctorById(doctorId);
    }


    @PostMapping("/isdoctorexist")
    private boolean isDoctorAlreadyExist(@RequestBody Doctor doctor) {
        if (doctorService.isDoctorAlreadyExist(doctor))
            return true;
        else
            return false;
    }

}
