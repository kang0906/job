package com.example.econrich.employees.repository;

import com.example.econrich.employees.entity.Employee;
import com.example.econrich.employees.entity.JobHistory;
import com.example.econrich.employees.entity.QJobHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.econrich.employees.entity.QJobHistory.jobHistory;

public class JobHistoryRepositoryImpl implements JobHistoryRepositoryCustom {

    private final JPAQueryFactory query;

    public JobHistoryRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<JobHistory> findByEmployeeId(Employee employee) {
        return query
                .select(jobHistory)
                .from(jobHistory)
                .join(jobHistory.job).fetchJoin()
                .join(jobHistory.department).fetchJoin()
                .where(jobHistory.jobHistoryId.employee.eq(employee))
                .fetch();
    }
}
