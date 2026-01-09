package com.example.Inxhinieri.Softi.report.model;

import com.example.Inxhinieri.Softi.report.enums.TargetType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode; // Import this
import java.sql.Types; // Import this
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.CHAR) // This fixes the error by matching the DB 'char' type
    @Column(name = "id", length = 36)
    private String id;

    @JdbcTypeCode(Types.CHAR) // Likely needed if reporterId is also a UUID/CHAR(36)
    @Column(name = "reporter_id")
    private String reporterId;

    @JdbcTypeCode(Types.CHAR) // Likely needed if targetId is also a UUID/CHAR(36)
    @Column(name = "target_id")
    private String targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    private String reason;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}