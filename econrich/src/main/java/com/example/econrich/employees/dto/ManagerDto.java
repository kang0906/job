package com.example.econrich.employees.dto;

import com.example.econrich.employees.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ManagerDto {
    private Integer employeeId;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private BigDecimal commissionPct;

    public ManagerDto(Employee manager) {
        this.employeeId = manager.getEmployeeId();
        this.firstName = manager.getFirstName();
        this.lastName = manager.getLastName();
        this.email = manager.getEmail();
        this.phoneNumber = manager.getPhoneNumber();
        this.hireDate = manager.getHireDate();
        this.commissionPct = manager.getCommissionPct();
    }
}
