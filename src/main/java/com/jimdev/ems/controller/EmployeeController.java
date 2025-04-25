package com.jimdev.ems.controller;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.dto.EmployeeResponseDto;
import com.jimdev.ems.mapper.EmployeeMapper;

import com.jimdev.ems.service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final EmployeeMapper employeeMapper;

    @Operation(summary = "Create a new employee",
            description = "This endpoint creates a new employee")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("")
    public ResponseEntity<EmployeeResponseDto> save(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        var employee = employeeService.save(employeeRequestDto);
        var employeeResponseDto = employeeMapper.toResponse(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDto);
    }
    @Operation(summary = "Get an employee by ID",
            description = "This endpoint retrieves an employee by their ID")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> findById(@PathVariable Long id) {
        var getEmployee = employeeService.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
        var employeeResponseDto = employeeMapper.toResponse(getEmployee);

        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDto);

    }

    @Operation(summary ="Get all employees",
            description = "This endpoint retrieves all employees")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "No employees found")
    })

    @GetMapping("")
    public ResponseEntity<List<EmployeeResponseDto>> findAll(){
        var listOfEmployees = employeeService.findAll();

        var responseList = listOfEmployees.stream()
                .map(employeeMapper:: toResponse)
                .toList();


        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @Operation(summary = "Delete an employee by ID",
            description = "This endpoint deletes an employee by their ID")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully!");
    }

    @Operation(summary = "Update an employee by ID",
            description = "This endpoint updates an employee by their ID")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
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

    @Operation(summary = "Update an employee by ID",
            description = "This endpoint updates an employee by their ID")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        var employee = employeeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.toResponse(employeeService.update(id, employeeRequestDto)));
    }



}
