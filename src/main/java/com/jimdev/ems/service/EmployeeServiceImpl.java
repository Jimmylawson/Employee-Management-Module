package com.jimdev.ems.service;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.model.Employee;
import com.jimdev.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    @Override
    public Employee save(EmployeeRequestDto employeeRequestDto) {
        return null;
    }

    @Override
    public Employee update(EmployeeRequestDto employeeRequestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }
}
