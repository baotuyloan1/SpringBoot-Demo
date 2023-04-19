package com.example.Springboot.demo.service;

import com.example.Springboot.demo.entity.Department;
import com.example.Springboot.demo.error.DepartmentNotFoundException;
import com.example.Springboot.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!(department.isPresent())){
            throw new DepartmentNotFoundException("Department Not Available");
        }

        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException {
        Optional <Department> depDB = departmentRepository.findById(departmentId);
        if (!depDB.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found to update");
        }else {
        Department departmentPresented = depDB.get();
        if (Objects.nonNull(department.getDepartmentName()) && !"".equals(departmentPresented.getDepartmentName())) {
            departmentPresented.setDepartmentName(department.getDepartmentName());
        }
        if (Objects.nonNull(department.getDepartmentCode()) && !"".equals(departmentPresented.getDepartmentCode())) {
            departmentPresented.setDepartmentCode(department.getDepartmentCode());
        }
        if (Objects.nonNull(department.getDepartmentAddress()) && !"".equals(departmentPresented.getDepartmentAddress())){
            departmentPresented.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(departmentPresented);
        }

    }

    @Override
    public Department fetchDepartmentByName(String name) {
        return departmentRepository.findByDepartmentNameIgnoreCase(name);
    }


}
