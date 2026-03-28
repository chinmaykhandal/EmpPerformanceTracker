package com.performancetracker.PerformanceTracker.controller;
import com.performancetracker.PerformanceTracker.dto.CycleSummaryDTO;
import com.performancetracker.PerformanceTracker.service.CycleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cycles")
public class CycleController {

    private final CycleService cycleService;

    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    /**
     * GET /cycles/{id}/summary
     */
    @GetMapping("/{id}/summary")
    public ResponseEntity<CycleSummaryDTO> getCycleSummary(@PathVariable Long id) {
        return ResponseEntity.ok(cycleService.getSummary(id));
    }
}