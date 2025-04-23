package com.jimdev.ems.mapper;

import com.jimdev.ems.dto.EmployeeRequestDto;
import com.jimdev.ems.dto.EmployeeResponseDto;
import com.jimdev.ems.model.Employee;
import org.mapstruct.*;

@Mapper(componentModel= "spring")
public interface EmployeeMapper {
    EmployeeResponseDto toResponse(Employee employee);
    Employee toEntity(EmployeeRequestDto employeeRequestDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee update(EmployeeRequestDto employeeRequestDto, @MappingTarget Employee employee);

}
