package com.perennial.doctorappointmentbooking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perennial.doctorappointmentbooking.dto.Response;
import com.perennial.doctorappointmentbooking.entity.Doctor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
public class DoctorRepositoryTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    //convert doctor object to string
    ObjectMapper objectMapper=new ObjectMapper();

    @Before
    public void setUp()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void addDoctorTest() throws Exception {
       Doctor doctor=new Doctor();
        doctor.setFirstName("Mohit");
        doctor.setLastName("Patil");
        doctor.setEmail("mp@gmail.com");
        doctor.setAddress("Pune");
        doctor.setSpeciality("Skin specialist");
        doctor.setEducation("MBBS");
        doctor.setStatus("APPOINTED");
        //convert into json string
        String jsonrequest=objectMapper.writeValueAsString(doctor);
       //give result into mvc format
        MvcResult mvcResult=mockMvc.perform(post("/doctors/doctors").content(jsonrequest)
                .content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent= mvcResult.getResponse().getContentAsString();
        Response response=objectMapper.readValue(resultContent,Response.class);
        Assert.assertTrue(response.isStatus()==Boolean.TRUE);
    }

    @Test
    public void getDoctorTest() throws Exception{
        MvcResult mvcResult=mockMvc.perform(get("/doctors/getdoctors")
                .content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent= mvcResult.getResponse().getContentAsString();
        Response response=objectMapper.readValue(resultContent,Response.class);
        Assert.assertTrue(response.isStatus()==Boolean.TRUE);
    }


}
