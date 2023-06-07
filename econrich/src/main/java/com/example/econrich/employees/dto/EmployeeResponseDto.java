package com.example.econrich.employees.dto;

import com.example.econrich.employees.entity.Employee;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeResponseDto {
    private Integer employeeId;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private BigDecimal salary;
    private BigDecimal commissionPct;
    private String jobId;
    private String jobTitle;
    private ManagerDto manager;
    private Integer departmentId;
    private String departmentName;

    public EmployeeResponseDto(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.hireDate = employee.getHireDate();
        this.salary = employee.getSalary();
        this.commissionPct = employee.getCommissionPct();

        this.jobId = employee.getJob().getJobId();
        this.jobTitle = employee.getJob().getJobTitle();

        if(employee.getManager()!= null){
            this.manager = new ManagerDto(employee.getManager());
        }

        if(employee.getDepartment()!= null){
            this.departmentId = employee.getDepartment().getDepartmentId();
            this.departmentName = employee.getDepartment().getDepartmentName();
        }
    }
}
