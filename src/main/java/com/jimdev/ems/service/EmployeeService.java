package com.jimdev.ems.service;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.model.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {
    Employee save(EmployeeRequestDto employeeRequestDto);
    Employee update(Long id, EmployeeRequestDto employeeRequestDto);
    void delete(Long id);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();


}
