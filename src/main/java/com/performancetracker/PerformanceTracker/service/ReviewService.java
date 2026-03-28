package com.performancetracker.PerformanceTracker.service;
import com.performancetracker.PerformanceTracker.dto.EmployeeReviewResponseDTO;
import com.performancetracker.PerformanceTracker.dto.ReviewDTO;
import com.performancetracker.PerformanceTracker.entity.Employee;
import com.performancetracker.PerformanceTracker.entity.PerformanceReview;
import com.performancetracker.PerformanceTracker.entity.ReviewCycle;
import com.performancetracker.PerformanceTracker.repository.EmployeeRepository;
import com.performancetracker.PerformanceTracker.repository.PerformanceReviewRepository;
import com.performancetracker.PerformanceTracker.repository.ReviewCycleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final PerformanceReviewRepository reviewRepository;
    private final EmployeeRepository employeeRepository;
    private final ReviewCycleRepository cycleRepository;

    public ReviewService(PerformanceReviewRepository reviewRepository,
                         EmployeeRepository employeeRepository,
                         ReviewCycleRepository cycleRepository) {
        this.reviewRepository = reviewRepository;
        this.employeeRepository = employeeRepository;
        this.cycleRepository = cycleRepository;
    }

    public PerformanceReview create(ReviewDTO dto) {
        if (dto.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee id is required");
        }
        if (dto.getReviewCycleId() == null) {
            throw new IllegalArgumentException("Review cycle id is required");
        }
        if (dto.getRating() < 1 || dto.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        ReviewCycle cycle = cycleRepository.findById(dto.getReviewCycleId())
                .orElseThrow(() -> new IllegalArgumentException("Review cycle not found"));

        PerformanceReview review = new PerformanceReview();
        review.setEmployee(employee);
        review.setReviewCycle(cycle);
        review.setRating(dto.getRating());
        review.setReviewerNotes(dto.getReviewerNotes());
        review.setSubmittedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public List<EmployeeReviewResponseDTO> getEmployeeReviews(Long employeeId) {
        if (employeeId == null) {
            throw new IllegalArgumentException("Employee id is required");
        }

        return reviewRepository.findEmployeeReviews(employeeId);
    }
}