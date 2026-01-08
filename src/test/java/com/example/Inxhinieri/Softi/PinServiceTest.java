package com.example.Inxhinieri.Softi;

import com.example.Inxhinieri.Softi.pin.services.PinService;
import com.example.Inxhinieri.Softi.pin.repository.PinRepository;
import com.example.Inxhinieri.Softi.pin.model.Pin;
import com.example.Inxhinieri.Softi.pin.dto.PinRequest;
import com.example.Inxhinieri.Softi.pin.dto.PinResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PinServiceTest {

    @Mock
    private PinRepository pinRepository;

    @InjectMocks
    private PinService pinService;

    private Pin samplePin;
    private final String pinId = "test-uuid-123";

    @BeforeEach
    void setUp() {
        samplePin = new Pin();
        samplePin.setId(pinId);
        samplePin.setName("Eiffel Tower");
        samplePin.setLatitude(48.8584);
        samplePin.setLongitude(2.2945);
        samplePin.setTrackId("track-99");
    }

    /* ---------------- CREATE ---------------- */

    @Test
    void krijoPin_ShouldReturnResponse_WhenSuccessful() {
        // Arrange
        PinRequest request = new PinRequest();
        request.name = "Eiffel Tower";
        request.latitude = 48.8584;
        request.longitude = 2.2945;
        request.trackId = "track-99";

        when(pinRepository.save(any(Pin.class))).thenReturn(samplePin);

        // Act
        PinResponse response = pinService.krijoPin(request);

        // Assert
        assertNotNull(response);
        assertEquals("Eiffel Tower", response.getName());
        verify(pinRepository, times(1)).save(any(Pin.class));
    }

    /* ---------------- READ ---------------- */

    @Test
    void getPinById_ShouldReturnPin_WhenExists() {
        // Arrange
        when(pinRepository.findById(pinId)).thenReturn(Optional.of(samplePin));

        // Act
        PinResponse response = pinService.getPinById(pinId);

        // Assert
        assertNotNull(response);
        assertEquals(pinId, response.getId());
    }

    @Test
    void getPinById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(pinRepository.findById("invalid")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> pinService.getPinById("invalid"));
    }

    @Test
    void getAllPins_ShouldReturnList() {
        // Arrange
        when(pinRepository.findAll()).thenReturn(Arrays.asList(samplePin));

        // Act
        List<PinResponse> result = pinService.getAllPins();

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Eiffel Tower", result.get(0).getName());
    }

    /* ---------------- UPDATE ---------------- */

    @Test
    void updatePin_ShouldUpdateFields_WhenExists() {
        // Arrange
        PinRequest updateRequest = new PinRequest();
        updateRequest.name = "New Name";

        when(pinRepository.findById(pinId)).thenReturn(Optional.of(samplePin));
        when(pinRepository.save(any(Pin.class))).thenReturn(samplePin);

        // Act
        PinResponse response = pinService.updatePin(pinId, updateRequest);

        // Assert
        assertEquals("New Name", response.getName());
        verify(pinRepository).save(samplePin);
    }

    /* ---------------- DELETE ---------------- */

    @Test
    void deletePin_ShouldCallRepository() {
        // Act
        pinService.deletePin(pinId);

        // Assert
        verify(pinRepository, times(1)).deleteById(pinId);
    }
}