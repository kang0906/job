package com.example.econrich.departments.controller;

import com.example.econrich.departments.dto.DepartmentResponseDto;
import com.example.econrich.departments.dto.DepartmentSalaryUpdateRequestDto;
import com.example.econrich.departments.dto.ResultResponseDto;
import com.example.econrich.departments.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/department/{departmentId}")
    public DepartmentResponseDto getDepartment(@PathVariable Integer departmentId){
        return departmentService.getDepartment(departmentId);
    }

    @PostMapping("/department/{departmentId}/salary")
    public ResultResponseDto updateDepartmentSalary(@PathVariable Integer departmentId, @RequestBody DepartmentSalaryUpdateRequestDto requestDto){
        return new ResultResponseDto(
                departmentService.updateDepartmentSalary(departmentId, requestDto)
        );
    }
}
