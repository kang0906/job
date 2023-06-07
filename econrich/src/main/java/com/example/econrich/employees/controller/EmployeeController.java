package com.example.econrich.employees.controller;

import com.example.econrich.employees.dto.EmployeeResponseDto;
import com.example.econrich.employees.dto.EmployeeUpdateRequestDto;
import com.example.econrich.employees.dto.EmployeeUpdateResponseDto;
import com.example.econrich.employees.dto.JobHistoryResponseDto;
import com.example.econrich.employees.entity.Employee;
import com.example.econrich.employees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee/{employeeId}")
    public EmployeeResponseDto getEmployee(@PathVariable Integer employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/employee/{employeeId}/history")
    public JobHistoryResponseDto getEmployeeHistory(@PathVariable Integer employeeId){
        return employeeService.getEmployeeHistory(employeeId);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeUpdateResponseDto updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeUpdateRequestDto requestDto){
        return employeeService.updateEmployee(employeeId, requestDto);
    }
}
