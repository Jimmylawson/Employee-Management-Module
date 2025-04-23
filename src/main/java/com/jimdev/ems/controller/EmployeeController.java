package com.jimdev.ems.controller;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.dto.EmployeeResponseDto;
import com.jimdev.ems.mapper.EmployeeMapper;
import com.jimdev.ems.model.Employee;
import com.jimdev.ems.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping("")
    public ResponseEntity<EmployeeResponseDto> save(EmployeeRequestDto employeeRequestDto) {
        var employeeResponseDto = employeeMapper.toResponse(employeeService.save(employeeRequestDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDto);
    }
    @GetMapping("/id")
    public ResponseEntity<EmployeeResponseDto> findById(Long id) {
        var employeeResponseDto = employeeMapper.toResponse(employeeService.findById(id));

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

    @DeleteMapping("/{id")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> patchEmployee(@PathVariable Long id, EmployeeRequestDto employeeRequestDto){
        var findEmployee = employeeService.findById(id);

        var employee = findEmployee.get();

        employeeMapper.update(employeeRequestDto, employee);

        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.toResponse(employee));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathEmployee Long id,EmployeeRequestDto employeeRequestDto){
        var employee = employeeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.toResponse(employeeService.update(id, employeeRequestDto)));
    }





}
