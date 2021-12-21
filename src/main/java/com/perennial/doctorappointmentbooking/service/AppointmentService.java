package com.perennial.doctorappointmentbooking.service;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.helper.AppointmentHelper;
import com.perennial.doctorappointmentbooking.repo.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    public void save(MultipartFile file) {
        try {
            List<Appointment> appointments = AppointmentHelper.convertExcelToListOfAppointment(file.getInputStream());

            appointments.forEach(s -> System.out.println(s.toString()));

            try {
                appointments.forEach(l -> appointmentRepository.save(l));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Appointment> findAllAppointmentsforDoctor(long doctorId) {

        List<Appointment> d = null;
        try {

            d = appointmentRepository.findAll()
                    .stream()
                    .filter(a -> (a.getDoctorId() == doctorId))
                    .collect(Collectors.toList());
            return d;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return d;
    }

    public List<Appointment> findAllAppointmentsforPatient(long patientId) {

        List<Appointment> d = null;
        try {

            d = appointmentRepository.findAll()
                    .stream()
                    .filter(a -> (a.getPatientId() == patientId))
                    .collect(Collectors.toList());
            return d;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return d;
    }

    public Appointment findByAppointmentId(int appointmentId) {
        return appointmentRepository.findByAppointmentId(appointmentId);
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {

        Appointment update = appointmentRepository.findByAppointmentId((int) appointment.getAppointmentId());
        update.setAppointmentDate(appointment.getAppointmentDate());
        update.setAppointmentStartTime(appointment.getAppointmentStartTime());
        update.setAppointmentEndTime(appointment.getAppointmentEndTime());
        update.setAppointmentStatus(appointment.getAppointmentStatus());
        update.setDoctorId(appointment.getDoctorId());
        update.setPatientId(appointment.getPatientId());
        return appointmentRepository.save(update);
    }


    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    public Appointment updateStatus(Long appointmentId, Appointment appointment) {

        Optional<Appointment> appointmentList = appointmentRepository.findById(appointmentId);

        if (appointmentList.isPresent()) {
            if (appointment.getAppointmentStatus() != null) {
                appointmentList.get().setAppointmentStatus(appointment.getAppointmentStatus());
            }
            return appointmentRepository.save(appointmentList.get());
        }
        return null;
    }

    public List<Appointment> findByPatientId(long patientId) {
        return findByPatientId(patientId);

    }
    public boolean isAppointmentBooked(Appointment appointment) {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        for (Appointment d : appointmentList) {
            if (d.getAppointmentDate().equals(appointment.getAppointmentDate())
                    && d.getAppointmentStatus().equals(appointment.getAppointmentStatus())
                    && d.getAppointmentStartTime().equals(appointment.getAppointmentStartTime())&& d.getAppointmentEndTime().equals(appointment.getAppointmentEndTime()));
                     return true;
            }


        return false;
        }

//    public  List<Appointment> findAllAppointmentOfTheDay(Date appointmentDate, Appointment appointment) {
//        List<Appointment> appointmentList = appointmentRepository.findAll();
//        for(Appointment a:appointmentList)
//        {
//            a.getAppointmentDate()
//        }
//
//    }


        }




