package com.example.Springboot.demo.service;

import com.example.Springboot.demo.entity.Department;
import com.example.Springboot.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;


    @MockBean
    private DepartmentRepository departmentRepository;


    @BeforeEach
    void setUp() {
        Department department = new Department().builder().departmentName("CE").departmentAddress("Da Nang").departmentCode("IT-06").departmentId(1L).build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("CE")).thenReturn(department);
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

    }

    @Test
    @DisplayName("Get Data based on Valid Department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
        String departmentName = "CE";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    @DisplayName("Save department")
    public void whenFetchDepartments_thenDepartmentsShouldFound(){
        Department department = new Department().builder().departmentName("CE").departmentAddress("Da Nang").departmentCode("IT-06").departmentId(1L).build();
        Department department1 = new Department().builder().departmentName("CE").departmentAddress("Da Nang").departmentCode("IT-06").departmentId(1L).build();

        if(department.equals(department1)){
            System.out.println("bằng nhau");
        }
        System.out.println("không bằng nhau");
        Department saved = departmentService.saveDepartment(department);
        assertEquals(department,saved);
    }
}