package com.example.Inxhinieri.Softi.guide.model;

import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty; // Import the enum
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "guide")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guide {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)", nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String location;

    // --- CHANGE STARTS HERE ---
    @Enumerated(EnumType.STRING) // Stores "Easy", "Medium", or "Hard" in DB
    @Column(name = "difficulty")
    private TrackDifficulty difficulty;
    // --- CHANGE ENDS HERE ---

    @Column(name = "base_price")
    private Double basePrice;

    @Column(name = "max_participants")
    private Integer maxParticipants;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    private String userId;

    @Column(name = "business_id", columnDefinition = "CHAR(36)")
    private String businessId;
}