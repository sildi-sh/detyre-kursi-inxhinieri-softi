package com.example.Inxhinieri.Softi.media.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateMediaDto {

    private String mediaUrl;

    private MediaType mediaType; // Photo or Video

    private String trackId;

    private String guideId;

    // No description, no name field as your entity doesn't have it
    public enum MediaType {
        Photo,
        Video
    }
}
