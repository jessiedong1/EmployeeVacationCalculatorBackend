package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.entity.HourlyEmployee;
import com.employee.entity.Manager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @Test
    void getAllEmployeeShouldWork() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/all");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void workShouldWork() throws Exception{
        RequestBuilder request=MockMvcRequestBuilders.get("/employee/updateworkday?id=1001&days=230")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isAccepted())
                .andExpect(content().string("8.0"))
                .andReturn();
    }
    @Test
    void takeVacationShouldReturnBadRequest() throws Exception{
        RequestBuilder request=MockMvcRequestBuilders.get("/employee/takevacation?id=1002&days=2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().string("0.0"))
                .andReturn();
    }

    @Test
    void takeVacationShouldWork() throws Exception{
        RequestBuilder request=MockMvcRequestBuilders.get("/employee/takevacation?id=1001&days=2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isAccepted())
                .andExpect(content().string("1.0"))
                .andReturn();
    }

    @Test
    void takeVacationShouldReturnNotFoundRequest() throws Exception{
        RequestBuilder request=MockMvcRequestBuilders.get("/employee/takevacation?id=101&days=2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().string("0.0"))
                .andReturn();
    }
}
