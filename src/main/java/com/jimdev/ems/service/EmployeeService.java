package com.jimdev.ems.service;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.dto.login.LoginRequestDto;
import com.jimdev.ems.model.Employee;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {
    Employee save(EmployeeRequestDto employeeRequestDto);
    Employee update(Long id, EmployeeRequestDto employeeRequestDto);
    void delete(Long id);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();
    public Employee savedUpdated(Employee employee);
    public  Optional<Employee> findByEmail(String email);

    String login( LoginRequestDto loginRequestDto);
}
