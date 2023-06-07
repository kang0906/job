package com.example.econrich.employees.repository;

import com.example.econrich.employees.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
