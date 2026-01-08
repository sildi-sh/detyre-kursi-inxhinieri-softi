package com.example.Inxhinieri.Softi.notification.dto;

public class UpdateNotificationDto {
  private String title;
  private String message;
  private CreateNotificationDto.Type type;
  private Boolean read;

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public CreateNotificationDto.Type getType() { return type; }
  public void setType(CreateNotificationDto.Type type) { this.type = type; }

  public Boolean getRead() { return read; }
  public void setRead(Boolean read) { this.read = read; }
}
