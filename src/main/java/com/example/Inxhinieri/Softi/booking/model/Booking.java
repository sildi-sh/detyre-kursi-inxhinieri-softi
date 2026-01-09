package com.example.Inxhinieri.Softi.booking.model;

import com.example.Inxhinieri.Softi.booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.CHAR)
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "guide_id")
    @JdbcTypeCode(Types.CHAR)
    private String guideId;

    @Column(name = "user_id")
    @JdbcTypeCode(Types.CHAR)
    private String userId;

    @Column(name = "slot_id")
    @JdbcTypeCode(Types.CHAR)
    private String slotId;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "num_participants", nullable = false)
    private int numParticipants;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @Setter(AccessLevel.NONE) // Prevents manual editing of the creation timestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}