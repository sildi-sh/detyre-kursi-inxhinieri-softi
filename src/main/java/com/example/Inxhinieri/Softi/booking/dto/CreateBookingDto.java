package com.example.Inxhinieri.Softi.booking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDto {
    private String userId;
    private String guideId;
    private int numParticipants;
    private LocalDateTime bookingDate;
}