package com.performancetracker.PerformanceTracker.controller;
import com.performancetracker.PerformanceTracker.dto.ReviewDTO;
import com.performancetracker.PerformanceTracker.entity.PerformanceReview;
import com.performancetracker.PerformanceTracker.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<PerformanceReview> createReview(@RequestBody ReviewDTO dto) {
        PerformanceReview savedReview = reviewService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
}