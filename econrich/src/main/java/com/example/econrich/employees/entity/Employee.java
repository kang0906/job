package com.example.econrich.employees.entity;

import com.example.econrich.departments.entity.Department;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Employee {
    @Id
    private Integer employeeId;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private BigDecimal salary;
    private BigDecimal commissionPct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public String changeInfo(String firstName, String lastName, String email, String phoneNumber){
        StringBuffer result = new StringBuffer("result: ");

        if( firstName != null && firstName.length() != 0){
            this.firstName = firstName;
            result.append(" / firstName Changed ");
        }else{
            result.append(" / firstName Change Fail ");
        }

        if(lastName != null && lastName.length() != 0){
            this.lastName = lastName;
            result.append(" / lastName Changed ");
        }else{
            result.append(" / lastName Change Fail ");
        }

        if(email != null && email.length() != 0 ){
            this.email = email;
            result.append(" / email Changed ");
        }else{
            result.append(" / email Change Fail ");
        }

        if(phoneNumber != null && phoneNumber.length() != 0 ){
            this.phoneNumber = phoneNumber;
            result.append(" / phoneNumber Changed ");
        }else{
            result.append(" / phoneNumber Change Fail ");
        }
        return result.toString();
    }

    public void changeJob(Job job){
        this.job = job;
    }

    public void changeSalary(BigDecimal salary){
        if(salary.compareTo(job.getMaxSalary()) < 1 && salary.compareTo(job.getMinSalary()) > -1 ){
            this.salary = salary;
        }else{
            throw new RuntimeException("Check Job(Max,min) salary");
        }
    }

    public void changeManager(Employee manager){
        this.manager = manager;
    }

    public void changeDepartment(Department department){
        this.department = department;
    }

    public void changeSalaryByPercent(int percent){
        BigDecimal bigDecimalPercent = new BigDecimal(percent).divide(new BigDecimal(100));
        BigDecimal newSalary = salary.add(salary.multiply(bigDecimalPercent));

        if(newSalary.compareTo(job.getMinSalary()) == -1){
            salary = job.getMinSalary();
            return;
        }

        if(newSalary.compareTo(job.getMaxSalary()) == 1){
            salary = job.getMaxSalary();
            return;
        }
        salary = newSalary;
    }

}

