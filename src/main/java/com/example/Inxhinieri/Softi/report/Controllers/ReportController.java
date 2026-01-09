package com.example.Inxhinieri.Softi.report.Controllers;

import com.example.Inxhinieri.Softi.report.dto.ReportDTO;
import com.example.Inxhinieri.Softi.report.model.Report;
import com.example.Inxhinieri.Softi.report.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody ReportDTO reportDTO) {
        return ResponseEntity.ok(reportService.createReport(reportDTO));
    }
}