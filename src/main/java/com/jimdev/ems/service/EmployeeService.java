package com.jimdev.ems.service;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.model.Employee;

import java.util.List;


public interface EmployeeService {
    Employee save(EmployeeRequestDto employeeRequestDto);
    Employee update(EmployeeRequestDto employeeRequestDto);
    void delete(Long id);
    Employee findById(Long id);
    List<Employee> findAll();

}
