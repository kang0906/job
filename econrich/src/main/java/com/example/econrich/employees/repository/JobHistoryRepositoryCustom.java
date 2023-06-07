package com.example.econrich.employees.repository;

import com.example.econrich.employees.entity.Employee;
import com.example.econrich.employees.entity.JobHistory;

import java.util.List;

public interface JobHistoryRepositoryCustom {
    List<JobHistory> findByEmployeeId(Employee employee);

}
