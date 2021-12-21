package com.perennial.doctorappointmentbooking;

import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.repo.DoctorRepository;
import com.perennial.doctorappointmentbooking.repo.HospitalRepository;
import com.perennial.doctorappointmentbooking.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@DataJpaTest
public class HospitalRepositoryTest {
    @Autowired
    HospitalService hospitalService;
    @MockBean
    HospitalRepository hospitalRepository;
    @Test
    public void getAllHospital()
    {
        when(hospitalRepository.findAll()).thenReturn(Stream.of(new Hospital(133L,"shree Ganesha","Pune",4532345678L)).collect(Collectors.toList()));
                assertEquals(2,hospitalService.getAllHospital().size());

    }
}
