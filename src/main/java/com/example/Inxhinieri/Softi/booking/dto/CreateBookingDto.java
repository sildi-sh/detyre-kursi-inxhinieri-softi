package com.example.Inxhinieri.Softi.booking.dto;

import java.time.LocalDateTime;

public class CreateBookingDto {
    private String userId;
    private String guideId;
    private int numParticipants;
    private LocalDateTime bookingDate;

    // Shto këto Getter-a që të zhduken vijat e kuqe te Service
    public String getUserId() { return userId; }
    public String getGuideId() { return guideId; }
    public int getNumParticipants() { return numParticipants; }
    public LocalDateTime getBookingDate() { return bookingDate; }
}