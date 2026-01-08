package com.example.Inxhinieri.Softi;

import com.example.Inxhinieri.Softi.report.model.Report;
import com.example.Inxhinieri.Softi.report.dto.ReportDTO;
import com.example.Inxhinieri.Softi.report.enums.ReportStatus;
import com.example.Inxhinieri.Softi.report.enums.TargetType;
import com.example.Inxhinieri.Softi.report.repository.ReportRepository;
import com.example.Inxhinieri.Softi.report.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    private Report sampleReport;

    @BeforeEach
    void setUp() {
        sampleReport = new Report();
        sampleReport.setId("1");
        sampleReport.setReporterId("user-123");
        sampleReport.setStatus(ReportStatus.PENDING);
    }

    @Test
    void createReport_ShouldSaveSuccessfully() {
        ReportDTO dto = new ReportDTO();
        dto.setReporterId("user-123");
        dto.setReason("Sherbim i dobet");
        dto.setTargetType(TargetType.GUIDE);

        when(reportRepository.save(any(Report.class))).thenReturn(sampleReport);
        Report result = reportService.createReport(dto);

        assertNotNull(result);
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void updateReportStatus_ShouldWork() {
        when(reportRepository.findById("1")).thenReturn(Optional.of(sampleReport));
        when(reportRepository.save(any(Report.class))).thenReturn(sampleReport);

        Report updated = reportService.updateReportStatus("1", ReportStatus.RESOLVED);

        assertNotNull(updated);
        assertEquals(ReportStatus.RESOLVED, updated.getStatus());
    }
}