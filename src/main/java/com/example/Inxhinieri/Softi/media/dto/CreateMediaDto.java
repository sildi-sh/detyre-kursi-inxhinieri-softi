package com.example.Inxhinieri.Softi.media.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateMediaDto {

    @NotBlank
    private String userId;

    private String trackId;

    private String guideId;

    @NotBlank
    private String mediaUrl;

    @NotNull
    private MediaType mediaType;

    public enum MediaType {
        Photo,
        Video
    }
}
