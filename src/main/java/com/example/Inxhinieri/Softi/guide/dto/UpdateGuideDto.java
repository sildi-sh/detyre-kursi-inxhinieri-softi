package com.example.Inxhinieri.Softi.guide.dto;

import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateGuideDto {
    private String title;
    private String description;
    private TrackDifficulty difficulty;
    private Double basePrice;
    private Boolean isActive;
}