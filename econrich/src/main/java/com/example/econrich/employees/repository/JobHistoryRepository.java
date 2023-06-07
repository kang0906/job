package com.example.econrich.employees.repository;

import com.example.econrich.employees.entity.JobHistory;
import com.example.econrich.employees.entity.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId>, JobHistoryRepositoryCustom {
}
