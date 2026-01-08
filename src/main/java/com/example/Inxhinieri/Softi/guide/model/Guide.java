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
    @Column(name = "id", length = 36)
    private String id = UUID.randomUUID().toString(); // Gjeneron automatikisht një ID unike

    @Column(nullable = false)
    private String title; // Nga diagrami: title : string

    @Column(columnDefinition = "LONGTEXT")
    private String description; // Nga diagrami: description : string

    private String location; // Nga diagrami: location : string

    private String difficulty; // Nga diagrami: difficulty : Difficulty (Enum)

    @Column(name = "base_price")
    private Double basePrice; // Nga diagrami: basePrice : decimal

    @Column(name = "max_participants")
    private Integer maxParticipants; // Nga diagrami: maxParticipants : int

    @Column(name = "is_active")
    private Boolean isActive = true; // Nga diagrami: isActive : boolean

    @Column(name = "user_id", length = 36)
    private String userId; // Lidhja me klasën User

    @Column(name = "business_id", length = 36)
    private String businessId; // Lidhja me BusinessProfile
}