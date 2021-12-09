package com.perennial.doctorappointmentbooking.controller;

import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
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
    @PostMapping("/addappointment")
    @ResponseBody
    public Appointment addAppointment(@RequestBody Appointment appointment)
    {
        return appointmentService.addAppointment(appointment);
    }

    @GetMapping("/appointment")
    public List<Appointment> getAllAppointment() {
        return this.appointmentService.getAllAppointment();
    }

    @RequestMapping("/updateappointment")
    public Appointment updateAppointment(Appointment appointment)
    {
        return this.appointmentService.updateAppointment(appointment);
    }
    @RequestMapping("/deleteappointment")
    public ResponseEntity<?> deleteAppointment(@PathVariable int appointment_id)
    {
        try {
            this.appointmentService.deleteAppointment(appointment_id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
