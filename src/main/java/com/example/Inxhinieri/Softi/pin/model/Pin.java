package com.example.Inxhinieri.Softi.pin.model;

import com.example.Inxhinieri.Softi.pin.enums.PinType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pin")
@Data
@NoArgsConstructor
public class Pin {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "track_id", nullable = false, columnDefinition = "CHAR(36)")
    private String trackId;

    @Column(name="latitude", nullable = false)
    private double latitude;

    @Column(name="longitude", nullable = false)
    private double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private PinType type;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
