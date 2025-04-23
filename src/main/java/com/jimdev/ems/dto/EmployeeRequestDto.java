package com.jimdev.ems.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;
    private String email;

}
