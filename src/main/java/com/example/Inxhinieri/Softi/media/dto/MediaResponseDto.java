package com.example.Inxhinieri.Softi.media.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MediaResponseDto {

    private String id;

    private String userId;

    private String trackId;

    private String guideId;

    private String mediaUrl;

    private MediaType mediaType;

    private LocalDateTime createdAt;

    public enum MediaType {
        Photo,
        Video
    }
}
