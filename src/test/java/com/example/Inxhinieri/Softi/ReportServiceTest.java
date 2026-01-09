package com.example.Inxhinieri.Softi;

import com.example.Inxhinieri.Softi.report.dto.ReportDTO;
import com.example.Inxhinieri.Softi.report.model.Report;
import com.example.Inxhinieri.Softi.report.repository.ReportRepository;
import com.example.Inxhinieri.Softi.report.services.ReportService;
import com.example.Inxhinieri.Softi.report.enums.TargetType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    public void testCreateReport() {
        // 1. Pergatitja e te dhenave (DTO)
        ReportDTO dto = new ReportDTO();
        dto.setReporterId("user123");
        dto.setTargetId("guide456");

        // Perdorim GUIDE sepse POST nuk ekzistonte ne TargetType.java
        dto.setTargetType(TargetType.GUIDE);
        dto.setReason("Spam content");

        // 2. Krijojme objektin qe presim te kthehet nga Mock-u
        Report expectedReport = new Report();
        expectedReport.setReporterId("user123");
        expectedReport.setTargetId("guide456");

        // 3. Mocking: Instruksionet per Repository-n virtual
        when(reportRepository.save(any(Report.class))).thenReturn(expectedReport);

        // 4. Ekzekutimi i metodes qe duam te testojme
        Report savedReport = reportService.createReport(dto);

        // 5. Verifikimi: A jane te dhenat korrekt?
        assertNotNull(savedReport);
        assertEquals("user123", savedReport.getReporterId());
    }
}