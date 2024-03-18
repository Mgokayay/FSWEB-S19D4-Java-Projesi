package com.workintech.s19d4.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {


    @MockBean
    private EmployeeService employeeService;

   @Autowired
    private MockMvc mockMvc;


    @Test
    void save() {
        Employee employee = new Employee();
        employee.setFirstName("Gokay");
        employee.setLastName("Ay");
        employee.setEmail("dk@dk.com");
        employee.setSalary(4000);

        when(employeeService.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee))
                .accept(MediaType.APPLICATION_JSON)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(4000)));


    }

    @Test
    void findByOrder() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName("Gokay");
        employee.setLastName("Ay");
        employee.setEmail("dk@dk.com");
        employee.setSalary(4000);
        employeeList.add(employee);

        when(employeeService.findByOrder()).thenReturn(employeeList);


        mockMvc.perform(get("/employee/order"))
                .andExpect(status().isOk());
        verify(employeeService).findByOrder();
    }

    private static String  asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }
}
