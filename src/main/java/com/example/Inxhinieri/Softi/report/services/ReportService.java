package com.example.Inxhinieri.Softi.report.services;

import com.example.Inxhinieri.Softi.report.model.Report;
import com.example.Inxhinieri.Softi.report.dto.ReportDTO;
import com.example.Inxhinieri.Softi.report.enums.ReportStatus; // Importi kyç
import com.example.Inxhinieri.Softi.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report createReport(ReportDTO dto) {
        Report report = new Report();
        report.setReporterId(dto.getReporterId());
        report.setTargetId(dto.getTargetId());
        report.setTargetType(dto.getTargetType());
        report.setReason(dto.getReason());

        // Përdorim ReportStatus.PENDING që IntelliJ ta gjejë fiks
        report.setStatus(ReportStatus.PENDING);

        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report updateReportStatus(String id, ReportStatus status) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(status);
        return reportRepository.save(report);
    }
}