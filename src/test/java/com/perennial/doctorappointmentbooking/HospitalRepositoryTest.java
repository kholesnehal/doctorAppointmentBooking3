package com.perennial.doctorappointmentbooking;
import com.perennial.doctorappointmentbooking.controller.HospitalController;
import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.repo.DoctorRepository;
import com.perennial.doctorappointmentbooking.repo.HospitalRepository;
import com.perennial.doctorappointmentbooking.service.HospitalService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class HospitalRepositoryTest {


//    HospitalService hospitalService;
//
//    @Mock
//    HospitalRepository hospitalRepository;
//
//     @BeforeEach
//     void setUp()
//{
//    this.hospitalService=new HospitalService(this.hospitalRepository);
//}
//    @Test
//    public void getAllHospitalTest()
//    {
//        hospitalService.getAllHospital();
//        verify(hospitalRepository).findAll();
//
//    }

}
