package com.example.econrich.employees.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jobs")
public class Job {
    @Id
    private String jobId;
    private String jobTitle;

    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
