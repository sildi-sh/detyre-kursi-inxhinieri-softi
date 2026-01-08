package com.example.Inxhinieri.Softi.notification.controllers;

import com.example.Inxhinieri.Softi.notification.dto.CreateNotificationDto;
import com.example.Inxhinieri.Softi.notification.dto.NotificationResponseDto;
import com.example.Inxhinieri.Softi.notification.dto.UpdateNotificationDto;
import com.example.Inxhinieri.Softi.notification.services.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  /* ---------------- CREATE ---------------- */
  @PostMapping
  public NotificationResponseDto create(@Valid @RequestBody CreateNotificationDto dto) {
    return notificationService.create(dto);
  }

  /* ---------------- READ ---------------- */
  @GetMapping
  public List<NotificationResponseDto> getAll() {
    return notificationService.findAll();
  }

  @GetMapping("/{id}")
  public NotificationResponseDto getById(@PathVariable String id) {
    return notificationService.findById(id);
  }

  /* ---------------- UPDATE ---------------- */
  @PutMapping("/{id}")
  public NotificationResponseDto update(
          @PathVariable String id,
          @RequestBody UpdateNotificationDto dto
  ) {
    return notificationService.update(id, dto);
  }

  /* ---------------- DELETE ---------------- */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    notificationService.delete(id);
  }
}
