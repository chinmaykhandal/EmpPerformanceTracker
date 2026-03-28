package com.performancetracker.PerformanceTracker.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class EmployeeFilterResponseDTO {

    private Long employeeId;
    private String name;
    private String department;
    private String role;
    private Double averageRating;
    private LocalDate joiningDate;

    public EmployeeFilterResponseDTO(
            Long employeeId,
            String name,
            String department,
            String role,
            LocalDate joiningDate,
            Double averageRating
    ) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.role = role;
        this.averageRating = averageRating;
        this.joiningDate = joiningDate;

    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}