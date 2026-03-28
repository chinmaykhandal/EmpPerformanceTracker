package com.performancetracker.PerformanceTracker.repository;

import com.performancetracker.PerformanceTracker.dto.EmployeeFilterResponseDTO;
import com.performancetracker.PerformanceTracker.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
        SELECT new com.performancetracker.PerformanceTracker.dto.EmployeeFilterResponseDTO(
            e.id,
            e.name,
            e.department,
            e.role,
            e.joiningDate,
            AVG(pr.rating)
        )
        FROM Employee e
        JOIN PerformanceReview pr ON pr.employee.id = e.id
        WHERE e.department = :department
        GROUP BY e.id, e.name, e.department, e.role
        HAVING AVG(pr.rating) >= :minRating""")
    List<EmployeeFilterResponseDTO> filterByDepartmentAndMinRating(@Param("department") String department, @Param("minRating") Double minRating);
}