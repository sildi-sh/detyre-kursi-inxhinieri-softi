package com.example.Inxhinieri.Softi.report.model;

import com.example.Inxhinieri.Softi.report.enums.ReportStatus;
import com.example.Inxhinieri.Softi.report.enums.TargetType;
import java.time.LocalDateTime;

public class Report {
    private String id;
    private String reporterId;
    private String targetId;
    private TargetType targetType;
    private String reason;
    private ReportStatus status;
    private LocalDateTime createdAt;

    // Konstruktori bosh
    public Report() {}

    // Getter dhe Setter manuale (Kjo heq erorret ne Service dhe Test)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getReporterId() { return reporterId; }
    public void setReporterId(String reporterId) { this.reporterId = reporterId; }
    public String getTargetId() { return targetId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }
    public TargetType getTargetType() { return targetType; }
    public void setTargetType(TargetType targetType) { this.targetType = targetType; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public ReportStatus getStatus() { return status; }
    public void setStatus(ReportStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}