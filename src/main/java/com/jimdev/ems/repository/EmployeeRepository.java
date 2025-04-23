package com.jimdev.ems.repository;

import com.jimdev.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsByEmail(String email);
}
