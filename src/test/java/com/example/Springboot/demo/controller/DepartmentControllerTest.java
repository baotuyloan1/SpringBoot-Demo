package com.example.Springboot.demo.controller;

import com.example.Springboot.demo.entity.Department;
import com.example.Springboot.demo.error.DepartmentNotFoundException;
import com.example.Springboot.demo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder().departmentAddress("Da Nang")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
       Department input = Department.builder().departmentAddress("Da Nang")
                .departmentCode("IT-06")
                .departmentName("IT")
                .build();
        Mockito.when(departmentService.saveDepartment(input)).thenReturn(department);
        mockMvc.perform(post("/department").content("{\n" +
                "    \"departmentName\":\"VKU\",\n" +
                "    \"departmentAddress\":\"Ha Noi\",\n" +
                "    \"departmentCode\":\"CE\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Long expectId = 1L;
        Mockito.when(departmentService.fetchDepartmentById(expectId)).thenReturn(department);
        mockMvc.perform(get("/department/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(
                jsonPath("$.departmentName").value(department.getDepartmentName())
        ).andExpect(jsonPath("$.departmentCode").value(department.getDepartmentCode()));
    }
}