package com.botscrew.university.repository;

import com.botscrew.university.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department getDepartmentByDepartmentName(String departmentName);
}
