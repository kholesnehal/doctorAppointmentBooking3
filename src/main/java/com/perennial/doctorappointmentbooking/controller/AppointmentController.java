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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @PostMapping("/upload")
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

@Autowired
DoctorRepo doctorRepo;
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;

    @PostMapping("/addappointment")
    private Doctor placeDoctor(@RequestBody Request request)
    {
        return doctorRepo.save(request.getDoctor());
    }
    @PostMapping("/addPatient")
    private Patient placePatient(@RequestBody Request request)
    {
        return patientRepo.save(request.getPatient());
    }

    @GetMapping("/findAllAppointment")
    public List<Appointment> findAllAppointment()
    {
        return appointmentRepo.findAll();
    }

@GetMapping("/getAppointmentById/{id}")
public  Appointment getAppointmentById(int appointment_id)
{
    return appointmentRepo.findByAppointmentId(appointment_id);
}


    @PutMapping("/updateappointment/{id}")
 public Appointment updateAppointment(@RequestBody Appointment appointment,@PathVariable int appointmnet_id)
    {
        return this.appointmentService.updateAppointment(appointment,appointmnet_id);
    }
    @DeleteMapping("/deleteappointment/{id}")
    public String deleteAppointment(@PathVariable int appointmentId)
    {
       return this.appointmentService.deleteAppointment(appointmentId);
    }

}
