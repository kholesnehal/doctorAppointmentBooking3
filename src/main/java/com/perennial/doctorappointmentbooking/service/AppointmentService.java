package com.perennial.doctorappointmentbooking.service;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements AppointmentInterface{
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

    public List<Appointment> findAllAppointmentsforDoctor(long doctorId) {

        List<Appointment>d=null;
        try {

            d=appointmentRepo.findAll()
                    .stream()
                    .filter(a ->(a.getDoctorId() == doctorId))
                    .collect(Collectors.toList());
            return d;
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return d;
    }
    public List<Appointment> findAllAppointmentsforPatient(long patientId) {

        List<Appointment>d=null;
        try {

            d=appointmentRepo.findAll()
                    .stream()
                    .filter(a ->(a.getPatientId() == patientId))
                    .collect(Collectors.toList());
            return d;
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return d;
    }

    public Appointment findByAppointmentId( int appointmentId)
    {
        return appointmentRepo.findByAppointmentId(appointmentId);
    }

    public Appointment addAppointment(Appointment appointment)
    {
        return appointmentRepo.save(appointment);
    }
    public Appointment updateAppointment(Appointment appointment) {

           Appointment update = appointmentRepo.findByAppointmentId((int) appointment.getAppointmentId());
       update.setAppointmentDate(appointment.getAppointmentDate());
        update.setAppointmentTime(appointment.getAppointmentTime());
        update.setAppointmentStatus(appointment.getAppointmentStatus());
        update.setDoctorId(appointment.getDoctorId());
        update.setPatientId(appointment.getPatientId());
            return appointmentRepo.save(update);
        }


    private void orElseThrow(Object a) {
    }

    public void deleteAppointment(Long appointmentId) {
    appointmentRepo.deleteById(appointmentId);
}
    public Appointment updateStatus(Long appointmentId, Appointment appointment) {

        Optional<Appointment> appointmentList = appointmentRepo.findById(appointmentId);

        if(appointmentList.isPresent()){
            if(appointment.getAppointmentStatus() != null){
                appointmentList.get().setAppointmentStatus(appointment.getAppointmentStatus());
            }
            return appointmentRepo.save(appointmentList.get());
        }
        return null;
    }

    public List<Appointment> findByPatientId(long patientId) {
        return findByPatientId(patientId);

    }
}
