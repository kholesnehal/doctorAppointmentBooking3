package com.perennial.doctorappointmentbooking.service;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepo;
import org.aspectj.weaver.patterns.ConcreteCflowPointcut;
import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//    public List<Appointment> getAllAppointment()
//    {
//        return appointmentRepo.findAll();
//    }

//    public Page<Appointment> getAllAppointmentforDoctor(Long id, Pageable pageable) {
//        return appointmentRepo.getAllAppointmentforDoctor(id, pageable);
//    }


List<Appointment>appointmentList=new ArrayList<>();

    public List<Appointment> findAllAppointmentsforDoctor(String status) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentStatus().equals(status))
                   {
                result.add(appointment);
            }
        }
        return result;
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
