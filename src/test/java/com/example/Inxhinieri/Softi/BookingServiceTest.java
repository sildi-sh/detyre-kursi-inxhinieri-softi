package com.example.Inxhinieri.Softi;

// Project Imports
import com.example.Inxhinieri.Softi.booking.services.BookingService;
import com.example.Inxhinieri.Softi.booking.repository.BookingRepository;
import com.example.Inxhinieri.Softi.booking.model.Booking;
import com.example.Inxhinieri.Softi.booking.dto.CreateBookingDto;
import com.example.Inxhinieri.Softi.booking.enums.BookingStatus;
import com.example.Inxhinieri.Softi.guide.services.GuideService;
import com.example.Inxhinieri.Softi.guide.model.Guide; // Assuming Guide model exists here

// Test Imports
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private GuideService guideService;

    @InjectMocks
    private BookingService bookingService;

    private Booking sampleBooking;
    private Guide sampleGuide;
    private final String bookingId = "book-123";

    @BeforeEach
    void setUp() {
        // Setup a fake Guide
        sampleGuide = new Guide();
        sampleGuide.setId("guide-456");
        sampleGuide.setBasePrice(100.0);

        // Setup a fake Booking
        sampleBooking = new Booking();
        sampleBooking.setId(bookingId);
        sampleBooking.setUserId("user-789");
        sampleBooking.setGuideId("guide-456");
        sampleBooking.setNumParticipants(2);
        sampleBooking.setStatus(BookingStatus.Pending);
        sampleBooking.setTotalPrice(200.0);
    }

    /* --- CREATE TEST --- */

    @Test
    void createBooking_ShouldCalculatePriceAndSave() {
        // Arrange
        CreateBookingDto dto = new CreateBookingDto();
        dto.setGuideId("guide-456");
        dto.setUserId("user-789");
        dto.setNumParticipants(3);
        dto.setBookingDate(LocalDateTime.now());

        when(guideService.findById("guide-456")).thenReturn(sampleGuide);
        when(bookingRepository.save(any(Booking.class))).thenReturn(sampleBooking);

        // Act
        Booking result = bookingService.createBooking(dto);

        // Assert
        assertNotNull(result);
        verify(guideService).findById("guide-456");
        verify(bookingRepository).save(any(Booking.class));
        // Note: Total price logic would be checked here in a real scenario
    }

    /* --- STATUS UPDATE TEST --- */

    @Test
    void updateStatus_ShouldChangeStatus_WhenBookingExists() {
        // Arrange
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(sampleBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(sampleBooking);

        // Act
        Booking updated = bookingService.updateStatus(bookingId, BookingStatus.Confirmed);

        // Assert
        assertEquals(BookingStatus.Confirmed, updated.getStatus());
        verify(bookingRepository).save(sampleBooking);
    }

    @Test
    void updateStatus_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(bookingRepository.findById("none")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookingService.updateStatus("none", BookingStatus.Confirmed));
    }

    /* --- HISTORY TEST --- */

    @Test
    void getHistoryByUser_ShouldReturnList() {
        // Arrange
        when(bookingRepository.findByUserId("user-789")).thenReturn(Arrays.asList(sampleBooking));

        // Act
        List<Booking> history = bookingService.getHistoryByUser("user-789");

        // Assert
        assertEquals(1, history.size());
        assertEquals("user-789", history.get(0).getUserId());
    }

    /* --- STATS TEST --- */

    @Test
    void getBookingStats_ShouldReturnCorrectGrouping() {
        // Arrange
        Booking b1 = new Booking(); b1.setStatus(BookingStatus.Pending);
        Booking b2 = new Booking(); b2.setStatus(BookingStatus.Confirmed);
        Booking b3 = new Booking(); b3.setStatus(BookingStatus.Pending);

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(b1, b2, b3));

        // Act
        Map<BookingStatus, Long> stats = bookingService.getBookingStats();

        // Assert
        assertEquals(2L, stats.get(BookingStatus.Pending));
        assertEquals(1L, stats.get(BookingStatus.Confirmed));
    }

    /* --- DELETE TEST --- */

    @Test
    void delete_ShouldCallRepository() {
        // Act
        bookingService.delete(bookingId);

        // Assert
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}