package com.jimdev.ems.controller;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.dto.EmployeeResponseDto;
import com.jimdev.ems.mapper.EmployeeMapper;

import com.jimdev.ems.model.Employee;
import com.jimdev.ems.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping("")
    public ResponseEntity<EmployeeResponseDto> save(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        var employee = employeeService.save(employeeRequestDto);
        var employeeResponseDto = employeeMapper.toResponse(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> findById(@PathVariable Long id) {
        var getEmployee = employeeService.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
        var employeeResponseDto = employeeMapper.toResponse(getEmployee);

        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDto);

    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeResponseDto>> findAll(){
        var listOfEmployees = employeeService.findAll();

        var responseList = listOfEmployees.stream()
                .map(employeeMapper:: toResponse)
                .toList();


        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> patchEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        //Finding existing employee
        var existingEmployee = employeeService.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found"));

        /// Use mapper to copy fields from the request to the existing item
        employeeMapper.update(employeeRequestDto, existingEmployee);

        /// saving it in the db
        var savedEmployee = employeeService.savedUpdated(existingEmployee);

        return ResponseEntity.ok(employeeMapper.toResponse(savedEmployee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        var employee = employeeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.toResponse(employeeService.update(id, employeeRequestDto)));
    }



}
