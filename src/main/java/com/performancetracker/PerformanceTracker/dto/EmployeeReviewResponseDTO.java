package com.performancetracker.PerformanceTracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeReviewResponseDTO {

    private Long reviewId;
    private int rating;
    private String reviewerNotes;
    private LocalDateTime submittedAt;

    private Long cycleId;
    private String cycleName;
    private LocalDate cycleStartDate;
    private LocalDate cycleEndDate;

    public EmployeeReviewResponseDTO(
            Long reviewId,
            int rating,
            String reviewerNotes,
            LocalDateTime submittedAt,
            Long cycleId,
            String cycleName,
            LocalDate cycleStartDate,
            LocalDate cycleEndDate
    ) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.reviewerNotes = reviewerNotes;
        this.submittedAt = submittedAt;
        this.cycleId = cycleId;
        this.cycleName = cycleName;
        this.cycleStartDate = cycleStartDate;
        this.cycleEndDate = cycleEndDate;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewerNotes() {
        return reviewerNotes;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public Long getCycleId() {
        return cycleId;
    }

    public String getCycleName() {
        return cycleName;
    }

    public LocalDate getCycleStartDate() {
        return cycleStartDate;
    }

    public LocalDate getCycleEndDate() {
        return cycleEndDate;
    }
}