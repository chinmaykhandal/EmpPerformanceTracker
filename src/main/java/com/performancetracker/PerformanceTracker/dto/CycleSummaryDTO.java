package com.performancetracker.PerformanceTracker.dto;

public class CycleSummaryDTO {

    private Double averageRating;
    private String topPerformer;
    private Long completedGoals;
    private Long missedGoals;

    public CycleSummaryDTO(Double averageRating,
                           String topPerformer,
                           Long completedGoals,
                           Long missedGoals) {
        this.averageRating = averageRating;
        this.topPerformer = topPerformer;
        this.completedGoals = completedGoals;
        this.missedGoals = missedGoals;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public String getTopPerformer() {
        return topPerformer;
    }

    public Long getCompletedGoals() {
        return completedGoals;
    }

    public Long getMissedGoals() {
        return missedGoals;
    }
}