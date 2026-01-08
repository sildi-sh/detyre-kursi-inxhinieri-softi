package com.example.Inxhinieri.Softi.notification.dto;

public class CreateNotificationDto {
  private String userId;
  private String title;   // matches entity
  private String message;
  private Type type;      // matches entity

  public enum Type {
    INFO, WARNING, ERROR
  }

  // Getters & Setters
  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public Type getType() { return type; }
  public void setType(Type type) { this.type = type; }
}
