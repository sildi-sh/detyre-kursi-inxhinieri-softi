package com.example.Inxhinieri.Softi.report.services;

import com.example.Inxhinieri.Softi.report.dto.ReportDTO;
import com.example.Inxhinieri.Softi.report.model.Report;
import com.example.Inxhinieri.Softi.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report createReport(ReportDTO reportDTO) {
        Report report = new Report();
        report.setReporterId(reportDTO.getReporterId());
        report.setTargetId(reportDTO.getTargetId());
        report.setTargetType(reportDTO.getTargetType());
        report.setReason(reportDTO.getReason());
        return reportRepository.save(report);
    }
}