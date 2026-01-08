package com.example.Inxhinieri.Softi;

// Project Imports
import com.example.Inxhinieri.Softi.track.enums.TrackDifficulty;
import com.example.Inxhinieri.Softi.track.services.TrackService;
import com.example.Inxhinieri.Softi.track.repository.TrackRepository;
import com.example.Inxhinieri.Softi.track.model.Track;
import com.example.Inxhinieri.Softi.track.dto.TrackRequest;
import com.example.Inxhinieri.Softi.track.dto.TrackResponse;

// Test Imports
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrackServiceTest {

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackService trackService;

    private Track sampleTrack;
    private final String trackId = "track-uuid-123";
    private final String userId = "user-abc-456";

    @BeforeEach
    void setUp() {
        sampleTrack = new Track();
        sampleTrack.setId(trackId);
        sampleTrack.setUserId(userId);
        sampleTrack.setName("Mountain Trail");
        sampleTrack.setDescription("A steep trail with great views.");
        sampleTrack.setDifficulty(TrackDifficulty.Hard);
        sampleTrack.setLength(12.5);
        sampleTrack.setIs_public(true);
    }

    /* ---------------- CREATE ---------------- */

    @Test
    void krijoTrack_ShouldReturnResponse_WhenSuccessful() {
        // Arrange
        TrackRequest request = new TrackRequest();
        request.setName("Mountain Trail");
        request.setDescription("A steep trail with great views.");
        request.setDifficulty(TrackDifficulty.Hard);
        request.setLength(12.5);
        request.setIs_public(true);

        when(trackRepository.save(any(Track.class))).thenReturn(sampleTrack);

        // Act
        TrackResponse response = trackService.krijoTrack(request, userId);

        // Assert
        assertNotNull(response);
        assertEquals("Mountain Trail", response.getName());
        assertEquals(userId, response.getUserId());
        verify(trackRepository, times(1)).save(any(Track.class));
    }

    /* ---------------- READ ---------------- */

    @Test
    void merrTrackById_ShouldReturnTrack_WhenExists() {
        // Arrange
        when(trackRepository.findById(trackId)).thenReturn(Optional.of(sampleTrack));

        // Act
        TrackResponse response = trackService.merrTrackById(trackId);

        // Assert
        assertNotNull(response);
        assertEquals(trackId, response.getId());
        assertEquals("Mountain Trail", response.getName());
    }

    @Test
    void merrTrackById_ShouldThrowResponseStatusException_WhenNotFound() {
        // Arrange
        when(trackRepository.findById("wrong-id")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> trackService.merrTrackById("wrong-id"));
    }
}