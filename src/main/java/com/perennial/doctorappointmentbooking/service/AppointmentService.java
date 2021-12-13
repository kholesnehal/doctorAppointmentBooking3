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

            appointments.forEach(s-> System.out.println(s.toString()));

            try {
                appointments.forEach(l -> appointmentRepo.save(l));

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

    public Appointment findByAppointmentId( int appointmentId)
    {
        return appointmentRepo.findByAppointmentId(appointmentId);
    }

    public Appointment addAppointment(Appointment appointment)
    {
        return appointmentRepo.save(appointment);
    }
    public Appointment updateAppointment(Appointment appointment,int appointment_id) {
        Appointment update =appointmentRepo.findByAppointmentId(appointment.getAppointmentId());
       update.setAppointmentDate(appointment.getAppointmentDate());
        update.setAppointmentTime(appointment.getAppointmentTime());
     update.setAppointmentStatus(appointment.getAppointmentStatus());
        return appointmentRepo.save(update);
    }


    public String deleteAppointment(int appointmentId) {
       Appointment appointment = appointmentRepo.findByAppointmentId(appointmentId);
        if (!ObjectUtils.isEmpty(appointmentId)) {
            appointmentRepo.delete(appointment);
            return " Record deleted = " + appointment;
        } else
            return "Record is not available and not deleted";
    }
}
