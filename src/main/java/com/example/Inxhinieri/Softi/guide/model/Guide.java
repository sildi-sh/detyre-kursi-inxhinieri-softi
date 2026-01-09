package com.example.Inxhinieri.Softi.guide.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    // Duke vendosur columnDefinition "CHAR(36)", kodi tani perputhet 100% me DB
    @Column(name = "id", columnDefinition = "CHAR(36)", nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String location;

    private String difficulty;

    @Column(name = "base_price")
    private Double basePrice;

    @Column(name = "max_participants")
    private Integer maxParticipants;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // Edhe lidhjet me User dhe Business duhet te jene CHAR(36) nese ID-te e tyre jane te tilla
    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    private String userId;

    @Column(name = "business_id", columnDefinition = "CHAR(36)")
    private String businessId;
}