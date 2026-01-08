package com.example.Inxhinieri.Softi.report.model;


import com.example.Inxhinieri.Softi.report.enums.ReportStatus;
import com.example.Inxhinieri.Softi.report.enums.TargetType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Report {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "reporter_id", length = 36, nullable = false)
    private String reporterId;

    @Column(name = "target_id", length = 36, nullable = false)
    private String targetId;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    private TargetType targetType;

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.OPEN;

    @Column(name = "handled_by", length = 36)
    private String handledBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}