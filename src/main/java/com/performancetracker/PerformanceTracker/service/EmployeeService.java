package com.performancetracker.PerformanceTracker.service;

import com.performancetracker.PerformanceTracker.dto.EmployeeFilterResponseDTO;
import com.performancetracker.PerformanceTracker.entity.Employee;
import com.performancetracker.PerformanceTracker.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new IllegalArgumentException("Employee name is required");
        }
        if (employee.getDepartment() == null || employee.getDepartment().isBlank()) {
            throw new IllegalArgumentException("Department is required");
        }
        if (employee.getRole() == null || employee.getRole().isBlank()) {
            throw new IllegalArgumentException("Role is required");
        }
        if (employee.getJoiningDate() == null) {
            throw new IllegalArgumentException("Joining date is required");
        }

        return employeeRepository.save(employee);
    }

    public List<EmployeeFilterResponseDTO> filterEmployees(
            String department,
            Double minRating
    ) {
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException("Department is required");
        }

        if (minRating == null || minRating < 1 || minRating > 5) {
            throw new IllegalArgumentException("Min rating must be between 1 and 5");
        }

        return employeeRepository.filterByDepartmentAndMinRating(
                department,
                minRating
        );
    }
}