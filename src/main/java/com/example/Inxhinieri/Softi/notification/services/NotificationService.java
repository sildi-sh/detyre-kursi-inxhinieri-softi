package com.example.Inxhinieri.Softi.notification.services;

import com.example.Inxhinieri.Softi.notification.dto.CreateNotificationDto;
import com.example.Inxhinieri.Softi.notification.dto.NotificationResponseDto;
import com.example.Inxhinieri.Softi.notification.dto.UpdateNotificationDto;
import com.example.Inxhinieri.Softi.notification.model.Notification;
import com.example.Inxhinieri.Softi.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  /* ---------------- CREATE ---------------- */
  public NotificationResponseDto create(CreateNotificationDto dto) {
    Notification notification = new Notification();
    notification.setUserId(dto.getUserId());
    notification.setMessage(dto.getMessage());
    Notification saved = notificationRepository.save(notification);
    return toResponseDto(saved);
  }

  /* ---------------- READ ---------------- */
  public List<NotificationResponseDto> findAll() {
    return notificationRepository.findAll()
            .stream()
            .map(this::toResponseDto)
            .collect(Collectors.toList());
  }

  public NotificationResponseDto findById(String id) {
    Notification notification = notificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notification not found"));
    return toResponseDto(notification);
  }

  /* ---------------- UPDATE ---------------- */
  public NotificationResponseDto update(String id, UpdateNotificationDto dto) {
    Notification notification = notificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notification not found"));

    if (dto.getMessage() != null) notification.setMessage(dto.getMessage());
    Notification updated = notificationRepository.save(notification);
    return toResponseDto(updated);
  }

  /* ---------------- DELETE ---------------- */
  public void delete(String id) {
    if (!notificationRepository.existsById(id)) {
      throw new RuntimeException("Notification not found");
    }
    notificationRepository.deleteById(id);
  }

  /* ---------------- MAPPER ---------------- */
  private NotificationResponseDto toResponseDto(Notification notification) {
    NotificationResponseDto dto = new NotificationResponseDto();
    dto.setId(notification.getId());
    dto.setUserId(notification.getUserId());
    dto.setMessage(notification.getMessage());
    dto.setCreatedAt(notification.getCreatedAt());
    return dto;
  }
}
