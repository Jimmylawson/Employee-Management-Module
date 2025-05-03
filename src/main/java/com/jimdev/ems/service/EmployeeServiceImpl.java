package com.jimdev.ems.service;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.exceptions.EmployeeNotFoundException;

import com.jimdev.ems.mapper.EmployeeMapper;
import com.jimdev.ems.model.Employee;
import com.jimdev.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Employee save( EmployeeRequestDto employeeRequestDto) {
      if(employeeRepository.findByEmail(employeeRequestDto.getEmail()).isPresent()) {
          throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exist");
      }
        // Encode password
        employeeRequestDto.setPassword(passwordEncoder.encode(employeeRequestDto.getPassword()));

      return employeeRepository.save(employeeMapper.toEntity(employeeRequestDto));

    }


    @Override
    public Employee update(Long id,EmployeeRequestDto employeeRequestDto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));

        employeeMapper.update(employeeRequestDto, existingEmployee);

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
        employeeRepository.deleteById(employee.getId());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee savedUpdated(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

}
