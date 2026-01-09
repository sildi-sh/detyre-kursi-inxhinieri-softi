package com.example.Inxhinieri.Softi.notification.dto;

import java.time.LocalDateTime;

public class NotificationResponseDto {
  private String id;
  private String userId;
  private String message;
  private LocalDateTime createdAt;

  // Getters & Setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
