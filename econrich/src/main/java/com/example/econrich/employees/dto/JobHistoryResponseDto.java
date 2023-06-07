package com.example.econrich.employees.dto;

import com.example.econrich.employees.entity.Employee;
import com.example.econrich.employees.entity.JobHistory;
import com.example.econrich.employees.entity.JobHistoryId;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JobHistoryResponseDto {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<History> historyList;


    public JobHistoryResponseDto(Employee employee, List<JobHistory> jobHistoryList) {
        this.employeeId = employee.getEmployeeId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.historyList = jobHistoryList.stream().map((e)-> new History(e)).toList();

    }

    @Data
    public class History{
        private String jobId;
        private String jobTitle;

        private LocalDate startDate;
        private LocalDate endDate;

        private Integer departmentId;
        private String departmentName;

        public History(JobHistory jobHistory) {
            this.jobId = jobHistory.getJob().getJobId();
            this.jobTitle = jobHistory.getJob().getJobTitle();
            this.startDate = jobHistory.getJobHistoryId().getStartDate();
            this.endDate = jobHistory.getEndDate();
            this.departmentId = jobHistory.getDepartment().getDepartmentId();
            this.departmentName = jobHistory.getDepartment().getDepartmentName();
        }
    }
}
