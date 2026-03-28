package com.performancetracker.PerformanceTracker.repository;

import com.performancetracker.PerformanceTracker.dto.CycleSummaryDTO;
import com.performancetracker.PerformanceTracker.dto.EmployeeReviewResponseDTO;
import com.performancetracker.PerformanceTracker.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {

    @Query("""
        SELECT AVG(pr.rating)
        FROM PerformanceReview pr
        WHERE pr.reviewCycle.id = :cycleId
    """)
    Double getAverageRating(@Param("cycleId") Long cycleId);

    @Query(value = """
        SELECT e.name
        FROM performance_review pr
        JOIN employee e
          ON pr.employee_id = e.id
        WHERE pr.review_cycle_id = :cycleId
        GROUP BY e.id, e.name
        ORDER BY AVG(pr.rating) DESC
        LIMIT 1
    """, nativeQuery = true)
    String findTopPerformer(@Param("cycleId") Long cycleId);

    @Query("SELECT new com.performancetracker.PerformanceTracker.dto.EmployeeReviewResponseDTO(pr.id, pr.rating, pr.reviewerNotes, pr.submittedAt, rc.id, rc.name, rc.startDate, rc.endDate) " +
            "FROM PerformanceReview pr JOIN pr.reviewCycle rc WHERE pr.employee.id = :employeeId " +
            "ORDER BY rc.startDate DESC")
    List<EmployeeReviewResponseDTO> findEmployeeReviews(
            @Param("employeeId") Long employeeId
    );
}

