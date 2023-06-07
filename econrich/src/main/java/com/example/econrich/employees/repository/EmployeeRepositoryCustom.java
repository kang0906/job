package com.example.econrich.employees.repository;

import com.example.econrich.departments.entity.Department;
import com.example.econrich.employees.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    Employee findByIdCustom(Integer employeeId);
    List<Employee> findByDepartmentWithJob(Department department);
}
