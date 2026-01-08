package com.example.Inxhinieri.Softi.track.model;

import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator; // <-- NEW IMPORT

@Entity
@Table(name = "track")
@Data
@NoArgsConstructor
public class Track {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "user_id", nullable = false, columnDefinition = "CHAR(36)")
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private TrackDifficulty difficulty;

    @Column(name = "length", nullable = false)
    private Double length;

    @Column(name = "is_public", nullable = false)
    private Boolean is_public = false;
}