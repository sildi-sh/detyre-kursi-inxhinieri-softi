package com.example.Inxhinieri.Softi.notification.dto;

public class CreateNotificationDto {
  private String userId;
  private String message;

  // Getters & Setters
  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
}
