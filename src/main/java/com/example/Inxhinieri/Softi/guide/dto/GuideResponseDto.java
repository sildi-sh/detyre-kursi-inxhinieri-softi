package com.example.Inxhinieri.Softi.guide.dto;

public class GuideResponseDto {
    private String id;
    private String title;
    private String description;
    private String location;
    private String difficulty;
    private Double basePrice;
    private Integer maxParticipants;
    private Boolean isActive;

    // KONSTRUKTORI
    public GuideResponseDto() {}

    // GETTERS DHE SETTERS (Këto i duhen Service-it)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Double getBasePrice() { return basePrice; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }

    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}