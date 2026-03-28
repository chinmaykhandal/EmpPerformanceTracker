package com.performancetracker.PerformanceTracker.repository;

import com.performancetracker.PerformanceTracker.entity.ReviewCycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCycleRepository extends JpaRepository<ReviewCycle, Long> {
}