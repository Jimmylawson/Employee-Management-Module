package com.jimdev.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeRequestDto {
    @NotBlank
    private String firstName;
   @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
}
