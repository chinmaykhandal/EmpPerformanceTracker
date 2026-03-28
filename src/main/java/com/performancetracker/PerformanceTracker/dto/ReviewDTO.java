package com.performancetracker.PerformanceTracker.dto;

public class ReviewDTO {

    private Long employeeId;
    private Long reviewCycleId;
    private int rating;
    private String reviewerNotes;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getReviewCycleId() {
        return reviewCycleId;
    }

    public void setReviewCycleId(Long reviewCycleId) {
        this.reviewCycleId = reviewCycleId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewerNotes() {
        return reviewerNotes;
    }

    public void setReviewerNotes(String reviewerNotes) {
        this.reviewerNotes = reviewerNotes;
    }
}