package com.perennial.doctorappointmentbooking.service;


import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepo appointmentRepo;


    public void save(MultipartFile file) {
        try {
            List<Appointment> appointments= AppointmentHelper.convertExcelToListOfAppointment(file.getInputStream());
//            appointmentRepo.saveAll(appointments);
            appointments.forEach(s-> System.out.println(s.toString()));

            try {
                appointments.forEach(l -> appointmentRepo.save(l));
//                appointments.forEach(s-> System.out.println(s.toString()));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch ( IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Appointment> getAllAppointment()
    {
        return appointmentRepo.findAll();
    }
    public Appointment addAppointment(Appointment appointment)
    {
        return appointmentRepo.save(appointment);
    }
    public Appointment updateAppointment(Appointment appointment) {
        Appointment update =appointmentRepo.findByAppointmentId(appointment.getAppointment_id());
        update.setAppointment_date(appointment.getAppointment_date());
        update.setAppointment_time(appointment.getAppointment_time());
        update.setAppointment_status(appointment.getAppointment_status());
        return appointmentRepo.save(update);
    }


    public String deleteAppointment(int appointment_id) {
       Appointment appointment = appointmentRepo.findByAppointmentId(appointment_id);
        if (!ObjectUtils.isEmpty(appointment_id)) {
            appointmentRepo.delete(appointment);
            return " Record deleted = " + appointment;
        } else
            return "Record is not available and not deleted";
    }
}
