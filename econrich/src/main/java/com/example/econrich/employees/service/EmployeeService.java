package com.example.econrich.employees.service;

import com.example.econrich.departments.repository.DepartmentRepository;
import com.example.econrich.employees.dto.EmployeeResponseDto;
import com.example.econrich.employees.dto.EmployeeUpdateRequestDto;
import com.example.econrich.employees.dto.EmployeeUpdateResponseDto;
import com.example.econrich.employees.dto.JobHistoryResponseDto;
import com.example.econrich.employees.entity.Employee;
import com.example.econrich.employees.repository.EmployeeRepository;
import com.example.econrich.employees.repository.JobHistoryRepository;
import com.example.econrich.employees.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobHistoryRepository jobHistoryRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeResponseDto getEmployee(Integer employeeId){
        return new EmployeeResponseDto(employeeRepository.findByIdCustom(employeeId));
    }

    public JobHistoryResponseDto getEmployeeHistory(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("No such Employee")); // todo : 예외 정의
        return new JobHistoryResponseDto(employee, jobHistoryRepository.findByEmployeeId(employee));
    }

    @Transactional
    public EmployeeUpdateResponseDto updateEmployee(Integer employeeId, EmployeeUpdateRequestDto requestDto) {
        EmployeeUpdateResponseDto responseDto = new EmployeeUpdateResponseDto();

        Employee employee = employeeRepository.findByIdCustom(employeeId);
        String result = employee.changeInfo(requestDto.getFirstName(), requestDto.getLastName(), requestDto.getEmail(), requestDto.getPhoneNumber());
        responseDto.setResult(result);

        if(requestDto.getSalary() != null){
            employee.changeSalary(requestDto.getSalary());
        }

        if(requestDto.getJobId() != null && requestDto.getJobId().length() > 0){
            employee.changeJob(jobRepository.findById(requestDto.getJobId())
                    .orElseThrow(() -> new RuntimeException("No such Job")));       // todo : 예외 정의
        }

        if(requestDto.getDepartmentId() != null){
            employee.changeDepartment(departmentRepository.findById(requestDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("No such Department")));// todo : 예외 정의
        }

        if(requestDto.getManagerId() != null){
            employee.changeManager(employeeRepository.findById(requestDto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("No such Employee")));// todo : 예외 정의
        }

        return responseDto;
    }
}
