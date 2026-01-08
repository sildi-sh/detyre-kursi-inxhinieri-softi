package com.example.Inxhinieri.Softi.report.services;

import com.example.Inxhinieri.Softi.report.model.Report;
import com.example.Inxhinieri.Softi.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report createReport(Report report) {
        if (report.getId() == null) {
            report.setId(UUID.randomUUID().toString());
        }
        return reportRepository.save(report);
    }

    public Report getReportById(String id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
    }
}
