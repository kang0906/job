package com.example.econrich.employees.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeUpdateRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String jobId;
    private BigDecimal salary;
    private Integer managerId;
    private Integer departmentId;

}
