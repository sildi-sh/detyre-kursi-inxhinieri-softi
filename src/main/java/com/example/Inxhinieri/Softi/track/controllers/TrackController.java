package com.example.Inxhinieri.Softi.track.controllers;

import com.example.Inxhinieri.Softi.track.dto.TrackRequest;
import com.example.Inxhinieri.Softi.track.dto.TrackResponse;
import com.example.Inxhinieri.Softi.track.services.TrackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * POST /api/tracks
     * Pika fundore për krijimin e një tracku të ri.
     * Kërkon autentikim.
     */
    @PostMapping
    public ResponseEntity<TrackResponse> krijoTrack(@Valid @RequestBody TrackRequest request) {
        // Zëvendësoni me marrjen e ID-së së vërtetë të përdoruesit nga JWT/SecurityContext
        String authenticatedUserId = "a979718b-0a62-4b48-b2fd-1c37d1c83b5e";
        TrackResponse response = trackService.krijoTrack(request, authenticatedUserId);

        // Kthen 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/tracks/{trackId}
     * Pika fundore për marrjen e të dhënave të një tracku specifik.
     */
    @GetMapping("/{trackId}")
    public ResponseEntity<TrackResponse> merrTrackById(@PathVariable String trackId) {
        TrackResponse response = trackService.merrTrackById(trackId);

        // Kthen 200 OK
        return ResponseEntity.ok(response);
    }
}