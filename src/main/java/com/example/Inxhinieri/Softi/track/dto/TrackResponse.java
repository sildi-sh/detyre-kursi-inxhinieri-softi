package com.example.Inxhinieri.Softi.track.dto;

import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackResponse {

    private String id;
    private String userId;
    private String name;
    private String description;
    private TrackDifficulty difficulty;
    private Double length;
    private Boolean is_public;

}