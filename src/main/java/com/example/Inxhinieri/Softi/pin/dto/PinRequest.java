package com.example.Inxhinieri.Softi.pin.dto;

import com.example.Inxhinieri.Softi.pin.enums.PinType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PinRequest {
    @NotBlank(message="TrackId is required")
    public String trackId;

    @NotBlank(message="Latitude is required")
    public Double latitude;

    @NotBlank(message="Longitude is required")
    public Double longitude;

    public PinType pinType;

    @NotNull(message="Name is required")
    public String name;

    public String description;
}
