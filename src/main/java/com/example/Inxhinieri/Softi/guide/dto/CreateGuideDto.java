package com.example.Inxhinieri.Softi.guide.dto;

public class CreateGuideDto {
    private String title;
    private String description;
    private String location;
    private String difficulty;
    private Double basePrice;
    private Integer maxParticipants;
    private String userId;
    private String businessId;

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDifficulty() { return difficulty; }
    public Double getBasePrice() { return basePrice; }
    public Integer getMaxParticipants() { return maxParticipants; }
    public String getUserId() { return userId; }
    public String getBusinessId() { return businessId; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setBusinessId(String businessId) { this.businessId = businessId; }
}