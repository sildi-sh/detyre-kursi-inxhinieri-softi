package com.example.Inxhinieri.Softi.report.dto;

import com.example.Inxhinieri.Softi.report.enums.ReportStatus;
import com.example.Inxhinieri.Softi.report.enums.TargetType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReportDTO {
    private String id;
    private String reporterId;
    private String targetId;
    private TargetType targetType;
    private ReportStatus status;
    private String handledBy;
    private LocalDateTime createdAt;
}