package com.perennial.doctorappointmentbooking;

import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.repo.DoctorRepository;
import com.perennial.doctorappointmentbooking.service.DoctorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class DoctorRepositoryTest {

    @Autowired
    DoctorService doctorService;
    @MockBean
    private DoctorRepository doctorRepository;
//    @Test
//    public void saveDoctorTest()
//    {
//        Doctor doctor=Doctor.builder()
//                .licenceNumber("356HG434JJ")
//                .firstName("Swapnil")
//                .lastName("Mhaske")
//                .email("sk@gmail.com")
//                .address("Mumbai")
//                .phone(4567897654L)
//                .speciality("heart specialist")
//                .experience(7)
//                .education("MBBS")
//                .status("APPOINTED").build();
//
//        doctorRepository.save(doctor);
//       assertThat(doctor.getDoctorId().isGreaterThan(0));
//    }

//    @Test
//    public void getDoctors()
//    {
//        when(doctorRepository.findAll()).thenReturn(Stream.of(new Doctor("356HG434JJ","manu","khole",
//                "sk2gmail.com","Pune",2345678987L,"heart specialist",7,"MBBS","APPOINTED")).collect(Collectors.toList()));
//
//    }

}
