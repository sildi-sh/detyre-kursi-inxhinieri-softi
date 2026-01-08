package com.example.Inxhinieri.Softi.booking.model;

import com.example.Inxhinieri.Softi.booking.enums.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // UUID sipas diagramës

    private String userId;
    private String guideId;

    private LocalDateTime bookingDate; // Data e udhëtimit
    private int numParticipants; // numParticipants sipas diagramës
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // Statusi (Pending, Confirmed, etj.)

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- GETTERS & SETTERS (Këto rregullojnë vijat e kuqe te Service) ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getGuideId() { return guideId; }
    public void setGuideId(String guideId) { this.guideId = guideId; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    public int getNumParticipants() { return numParticipants; }
    public void setNumParticipants(int numParticipants) { this.numParticipants = numParticipants; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}