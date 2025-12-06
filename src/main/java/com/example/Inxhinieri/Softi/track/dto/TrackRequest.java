package com.example.Inxhinieri.Softi.track.dto;

import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrackRequest {
    @NotBlank(message = "Emri është i detyrueshëm")
    public String name;

    public String description;

    @NotNull(message = "Vështirësia është e detyrueshme")
    public TrackDifficulty difficulty; // enum

    @NotNull(message = "Gjatësia është e detyrueshme")
    public Double length; // float

    @NotNull(message = "Statusi publik është i detyrueshëm")
    public Boolean is_public; // boolean

    public @NotNull(message = "Statusi publik është i detyrueshëm") Boolean getIsPublic() {
        return is_public;
    }
}