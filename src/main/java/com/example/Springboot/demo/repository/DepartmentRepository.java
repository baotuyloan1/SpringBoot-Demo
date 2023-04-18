package com.example.Springboot.demo.repository;

import com.example.Springboot.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByDepartmentName(String name);

    public Department findByDepartmentNameIgnoreCase(String name);

    public Department findDistinctByDepartmentNameIgnoreCase(String name);

}
