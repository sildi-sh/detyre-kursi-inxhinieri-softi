package com.example.Inxhinieri.Softi.track.services;

import com.example.Inxhinieri.Softi.track.dto.TrackRequest;
import com.example.Inxhinieri.Softi.track.dto.TrackResponse;
import com.example.Inxhinieri.Softi.track.model.Track;
import com.example.Inxhinieri.Softi.track.repository.TrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TrackService {

    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    /**
     * Krijon një track të ri duke përdorur të dhënat nga TrackRequest.
     * @param request Të dhënat e trackut.
     * @param currentUserId ID e përdoruesit të autentikuar.
     * @return TrackResponse me të dhënat e trackut të krijuar.
     */
    public TrackResponse krijoTrack(TrackRequest request, String currentUserId) {
        System.out.println("------------------------------------------");
        System.out.println("TRACK SERVICE DEBUGGING");
        System.out.println("Authenticated User ID: " + currentUserId);
        System.out.println("Request Name: " + request.getName());
        System.out.println("Request Description: " + request.getDescription());
        System.out.println("Request Difficulty: " + request.getDifficulty());
        System.out.println("Request Length: " + request.getLength());
        System.out.println("Request IsPublic: " + request.getIs_public());
        System.out.println("------------------------------------------");

        Track track = new Track();
        track.setId(UUID.randomUUID().toString());
        track.setUserId(currentUserId);
        track.setName(request.getName());
        track.setDescription(request.getDescription());
        track.setDifficulty(request.getDifficulty());
        track.setLength(request.getLength());
        track.setIs_public(request.getIs_public());

        Track savedTrack = trackRepository.save(track);

        // Kthen DTO-në
        return mapToResponse(savedTrack);
    }

    /**
     * Merr të dhënat e një tracku specifik.
     * @param trackId ID e trackut.
     * @return TrackResponse me të dhënat e trackut.
     */
    public TrackResponse merrTrackById(String trackId) {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Track-u nuk u gjet."));

        return mapToResponse(track);
    }

    /**
     * Metodë ndihmëse për konvertimin e Modelit në Response DTO.
     */
    private TrackResponse mapToResponse(Track track) {
        return TrackResponse.builder()
                .id(track.getId())
                .userId(track.getUserId())
                .name(track.getName())
                .description(track.getDescription())
                .difficulty(track.getDifficulty())
                .length(track.getLength())
                .is_public(track.getIs_public())
                .build();
    }
}