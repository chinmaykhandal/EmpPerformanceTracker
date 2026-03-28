package com.performancetracker.PerformanceTracker.controller;
import com.performancetracker.PerformanceTracker.dto.EmployeeFilterResponseDTO;
import com.performancetracker.PerformanceTracker.dto.EmployeeReviewResponseDTO;
import com.performancetracker.PerformanceTracker.entity.Employee;
import com.performancetracker.PerformanceTracker.entity.PerformanceReview;
import com.performancetracker.PerformanceTracker.service.EmployeeService;
import com.performancetracker.PerformanceTracker.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ReviewService reviewService;

    public EmployeeController(EmployeeService employeeService, ReviewService reviewService) {
        this.employeeService = employeeService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<EmployeeReviewResponseDTO>> getEmployeeReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getEmployeeReviews(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeFilterResponseDTO>> filterEmployees(@RequestParam String department, @RequestParam Double minRating
    ) {
        return ResponseEntity.ok(employeeService.filterEmployees(department, minRating)
        );
    }
}