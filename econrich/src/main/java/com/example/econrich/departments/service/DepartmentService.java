package com.example.econrich.departments.service;

import com.example.econrich.departments.dto.DepartmentResponseDto;
import com.example.econrich.departments.dto.DepartmentSalaryUpdateRequestDto;
import com.example.econrich.departments.entity.Department;
import com.example.econrich.departments.repository.DepartmentRepository;
import com.example.econrich.employees.entity.Employee;
import com.example.econrich.employees.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentResponseDto getDepartment(Integer departmentId){
        Department Department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("No Such Department"));
        return new DepartmentResponseDto(Department);
    }

    public String updateDepartmentSalary(Integer departmentId, DepartmentSalaryUpdateRequestDto requestDto){
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("No Such Department"));

        List<Employee> byDepartmentWithJob = employeeRepository.findByDepartmentWithJob(department);
        for (Employee employee : byDepartmentWithJob) {
            employee.changeSalaryByPercent(requestDto.getPercent());
        }

        return "Success";
    }

}
