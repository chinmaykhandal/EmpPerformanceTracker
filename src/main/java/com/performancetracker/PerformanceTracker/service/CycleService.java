package com.performancetracker.PerformanceTracker.service;
import com.performancetracker.PerformanceTracker.dto.CycleSummaryDTO;
import com.performancetracker.PerformanceTracker.entity.GoalStatus;
import com.performancetracker.PerformanceTracker.repository.GoalRepository;
import com.performancetracker.PerformanceTracker.repository.PerformanceReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class CycleService {

    private final PerformanceReviewRepository reviewRepository;
    private final GoalRepository goalRepository;

    public CycleService(PerformanceReviewRepository reviewRepository, GoalRepository goalRepository) {
        this.reviewRepository = reviewRepository;
        this.goalRepository = goalRepository;
    }

    public CycleSummaryDTO getSummary(Long cycleId) {
        if (cycleId == null) {
            throw new IllegalArgumentException("Cycle id is required");
        }

        Double averageRating = reviewRepository.getAverageRating(cycleId);

        String topPerformer = reviewRepository.findTopPerformer(cycleId);

        Long completedGoals = goalRepository.countByReviewCycleIdAndStatus(
                cycleId,
                GoalStatus.COMPLETED
        );

        Long missedGoals = goalRepository.countByReviewCycleIdAndStatus(
                cycleId, GoalStatus.MISSED);

        return new CycleSummaryDTO(
                averageRating,
                topPerformer,
                completedGoals,
                missedGoals
        );
    }
}