package com.example.Springboot.demo.repository;

import com.example.Springboot.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager entityManager;

    Long idDerpartment=-1L;
    @BeforeEach
    void setUp() {
//        Department department = Department.builder().departmentName("Mechanical Engineering").departmentCode("ME - 011").departmentAddress("Da Nang").build();
//        entityManager.persist(department);
//        Department department1 = Department.builder().departmentName("Mechanical Engineering1").departmentCode("ME - 011").departmentAddress("Da Nang").build();
//        entityManager.persist(department1);
        Department department1 = Department.builder().departmentName("Mechanical Engineering").departmentCode("ME - 011").departmentAddress("Da Nang").build();
        Department department2 = Department.builder().departmentName("Electrical Engineering").departmentCode("EE - 022").departmentAddress("Ho Chi Minh City").build();
        Department department3 = Department.builder().departmentName("Civil Engineering").departmentCode("CE - 033").departmentAddress("Hanoi").build();
        entityManager.persist(department1);
        entityManager.persist(department2);
        entityManager.persist(department3);
        idDerpartment = department1.getDepartmentId();


    }


    @Test
    @Disabled
    public void whenFindById_thenReturnDepartment() {
        Department department= departmentRepository.findById(idDerpartment).get();
        assertEquals(department.getDepartmentName(),"Mechanical Engineering");
    }

    @Test
    @DisplayName("find All Departments Repository test")
    public void whenFindAll_thenReturnDepartments(){
//        Department department = Department.builder().departmentName("Mechanical Engineering").departmentCode("ME - 011").departmentAddress("Da Nang").build();
//        Department department1 = Department.builder().departmentName("Mechanical Engineering1").departmentCode("ME - 011").departmentAddress("Da Nang").build();
//        List<Department> expectedDepartments = new ArrayList<>(Arrays.asList(department,department1));
//        List<Department> departments = departmentRepository.findAll();
//        assertTrue(departments.containsAll(expectedDepartments));

        Department department1 = Department.builder().departmentId(1L).departmentName("Mechanical Engineering").departmentCode("ME - 011").departmentAddress("Da Nang").build();
        Department department2 = Department.builder().departmentId(2L).departmentName("Electrical Engineering").departmentCode("EE - 022").departmentAddress("Ho Chi Minh City").build();
        Department department3 = Department.builder().departmentId(3L).departmentName("Civil Engineering").departmentCode("CE - 033").departmentAddress("Hanoi").build();



        List<Department> expectedDepartments = Arrays.asList(department1, department2, department3);
        List<Department> departments = departmentRepository.findAll();

        assertEquals(expectedDepartments.size(), departments.size());
        assertTrue(departments.containsAll(expectedDepartments));
    }

}