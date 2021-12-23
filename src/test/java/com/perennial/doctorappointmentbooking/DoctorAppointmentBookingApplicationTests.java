package com.perennial.doctorappointmentbooking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perennial.doctorappointmentbooking.dto.Response;
import com.perennial.doctorappointmentbooking.entity.Appointment;
import com.perennial.doctorappointmentbooking.entity.Hospital;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class DoctorAppointmentBookingApplicationTests {
private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    //convert hospital object to string
    ObjectMapper objectMapper=new ObjectMapper();

    @Before
    public void setUp()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
@Test
public void addHospitalTest() throws Exception {
    Hospital hospital=new Hospital();
    hospital.setHospitalAddress("Kolhapur");
    hospital.setHospitalName("Rubi");
    hospital.setPhone(6789589047L);
    //convert into json string
String jsonrequest=objectMapper.writeValueAsString(hospital);
//give result into mvc format
    MvcResult mvcResult=mockMvc.perform(post("/hospitals/addhospitals").content(jsonrequest)
            .content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
    String resultContent= mvcResult.getResponse().getContentAsString();
    Response response=objectMapper.readValue(resultContent,Response.class);
    Assert.assertTrue(response.isStatus()==Boolean.TRUE);
}

@Test
    public void getHospitalTest() throws Exception{
    MvcResult mvcResult=mockMvc.perform(get("/hospitals/hospitals")
            .content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
    String resultContent= mvcResult.getResponse().getContentAsString();
    Response response=objectMapper.readValue(resultContent,Response.class);
    Assert.assertTrue(response.isStatus()==Boolean.TRUE);
}

}
