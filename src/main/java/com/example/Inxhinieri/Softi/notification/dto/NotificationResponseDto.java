package com.example.Inxhinieri.Softi.notification.dto;

import java.time.LocalDateTime;

public class NotificationResponseDto {
  private String id;
  private String userId;
  private String title;
  private String message;
  private Type type;
  private boolean read;
  private LocalDateTime createdAt;

  public enum Type {
    INFO, WARNING, ERROR
  }

  // Getters & Setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public Type getType() { return type; }
  public void setType(Type type) { this.type = type; }

  public boolean isRead() { return read; }
  public void setRead(boolean read) { this.read = read; }

  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
