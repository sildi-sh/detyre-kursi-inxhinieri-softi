package com.example.Inxhinieri.Softi.guide.dto;

import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGuideDto {
    private String title;
    private String description;
    private String location;

    // Now using the Enum instead of String
    private TrackDifficulty difficulty;

    private Double basePrice;
    private Integer maxParticipants;
    private String userId;
    private String businessId;
}