package com.example.Inxhinieri.Softi.report.model;

import com.example.Inxhinieri.Softi.report.enums.TargetType;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String reporterId;
    private String targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    private String reason;
    private LocalDateTime createdAt = LocalDateTime.now();
}