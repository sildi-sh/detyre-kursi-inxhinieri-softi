package com.example.Inxhinieri.Softi.media.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;

@Entity
@Table(name = "media_post")
@Data
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    private String userId;

    @Column(name = "track_id", columnDefinition = "CHAR(36)")
    private String trackId;

    @Column(name = "guide_id", columnDefinition = "CHAR(36)")
    private String guideId;

    @Column(name = "media_url", columnDefinition = "TEXT")
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType; // enum for Photo / Video

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public enum MediaType {
        Photo, Video
    }
}
