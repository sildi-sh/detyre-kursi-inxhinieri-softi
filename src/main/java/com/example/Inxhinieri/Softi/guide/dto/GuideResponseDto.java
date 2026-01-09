package com.example.Inxhinieri.Softi.guide.dto;

import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuideResponseDto {
    private String id;
    private String title;
    private String description;
    private String location;

    private TrackDifficulty difficulty;

    private Double basePrice;
    private Integer maxParticipants;
    private Boolean isActive;
}