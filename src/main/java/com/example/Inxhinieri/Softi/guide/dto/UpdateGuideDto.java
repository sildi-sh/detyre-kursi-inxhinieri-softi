package com.example.Inxhinieri.Softi.guide.dto;

public class UpdateGuideDto {
    private String title;
    private String description;
    private String difficulty;
    private Double basePrice;
    private Boolean isActive;

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDifficulty() { return difficulty; }
    public Double getBasePrice() { return basePrice; }
    public Boolean getIsActive() { return isActive; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setBasePrice(Double basePrice) { this.basePrice = basePrice; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}