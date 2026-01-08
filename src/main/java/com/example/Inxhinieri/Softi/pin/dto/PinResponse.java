package com.example.Inxhinieri.Softi.pin.dto;



import com.example.Inxhinieri.Softi.pin.enums.PinType;
import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PinResponse {

    private String id;
    private String trackId;
    private Double latitude;
    private Double longitude;
    private PinType type;
    private String name;
    private String description;

}