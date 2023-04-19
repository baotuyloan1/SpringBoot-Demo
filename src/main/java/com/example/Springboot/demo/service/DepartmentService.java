package com.example.Springboot.demo.service;

import com.example.Springboot.demo.entity.Department;
import com.example.Springboot.demo.error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException;

    Department fetchDepartmentByName(String name);
}
