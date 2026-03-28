package com.performancetracker.PerformanceTracker.repository;

import com.performancetracker.PerformanceTracker.entity.Goals;
import com.performancetracker.PerformanceTracker.entity.GoalStatus;
import com.performancetracker.PerformanceTracker.entity.Goals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goals, Long> {

    Long countByReviewCycleIdAndStatus(Long cycleId, GoalStatus goalStatus);
}