package com.example.econrich.employees.repository;

import com.example.econrich.departments.entity.Department;
import com.example.econrich.departments.entity.QDepartment;
import com.example.econrich.employees.entity.Employee;
import com.example.econrich.employees.entity.QEmployee;
import com.example.econrich.employees.entity.QJob;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.econrich.employees.entity.QEmployee.employee;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{

    private final JPAQueryFactory query;

    public EmployeeRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Employee findByIdCustom(Integer employeeId) {

        return query
                .select(employee)
                .from(employee)
                .join(employee.job, QJob.job).fetchJoin()
                .leftJoin(employee.manager).fetchJoin()
                .leftJoin(employee.department, QDepartment.department).fetchJoin()
                .where(employee.employeeId.eq(employeeId))
                .fetchOne();
    }

    @Override
    public List<Employee> findByDepartmentWithJob(Department department) {
        return query
                .select(employee)
                .from(employee)
                .join(employee.job, QJob.job).fetchJoin()
                .where(employee.department.eq(department))
                .fetch();
    }
}
