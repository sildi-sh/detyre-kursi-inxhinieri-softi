package com.example.Inxhinieri.Softi.notification.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
public class Notification {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "CHAR(36)")
  private String id;

  @Column(name = "user_id", columnDefinition = "CHAR(36)", nullable = false)
  private String userId;

  @Column(name = "title", nullable = false)
  private String title;   // <--- Add this field

  @Column(name = "message", columnDefinition = "TEXT", nullable = false)
  private String message;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "read", nullable = false)
  private boolean read;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private Type type;

  public enum Type {
    INFO, WARNING, ERROR
  }

  @PrePersist
  public void prePersist() {
    if (createdAt == null) {
      createdAt = LocalDateTime.now();
    }
  }
}
