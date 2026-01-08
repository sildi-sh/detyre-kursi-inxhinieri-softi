package com.example.Inxhinieri.Softi.report.dto;

import com.example.Inxhinieri.Softi.report.enums.TargetType;

public class ReportDTO {
    private String reporterId;
    private String targetId;
    private TargetType targetType;
    private String reason;

    public String getReporterId() { return reporterId; }
    public void setReporterId(String reporterId) { this.reporterId = reporterId; }
    public String getTargetId() { return targetId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }
    public TargetType getTargetType() { return targetType; }
    public void setTargetType(TargetType targetType) { this.targetType = targetType; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}