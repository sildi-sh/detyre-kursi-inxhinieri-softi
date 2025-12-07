package com.example.Inxhinieri.Softi.pin.services;

import com.example.Inxhinieri.Softi.pin.dto.PinRequest;
import com.example.Inxhinieri.Softi.pin.dto.PinResponse;
import com.example.Inxhinieri.Softi.pin.model.Pin;
import com.example.Inxhinieri.Softi.pin.repository.PinRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PinService {
    private final PinRepository pinRepository;

    public PinService(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    public PinResponse krijoPin(PinRequest request) {
        Pin pin = new Pin();
        pin.setTrackId(request.trackId);
        pin.setId(UUID.randomUUID().toString());
        pin.setLatitude(request.latitude);
        pin.setLongitude(request.longitude);
        pin.setType(request.pinType);
        pin.setName(request.name);
        pin.setDescription(request.description);

        Pin savedPin = pinRepository.save(pin);
        return mapToResponse(savedPin);
    }

    public PinResponse getPinById(String id) {
        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pin not found with id: " + id));
        return mapToResponse(pin);
    }

    public PinResponse updatePin(String id, PinRequest request) {
        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pin not found with id: " + id));

        // Update only what is provided
        if (request.latitude != null) pin.setLatitude(request.latitude);
        if (request.longitude != null) pin.setLongitude(request.longitude);
        if (request.pinType != null) pin.setType(request.pinType);
        if (request.name != null) pin.setName(request.name);
        if (request.description != null) pin.setDescription(request.description);
        if (request.trackId != null) pin.setTrackId(request.trackId);

        Pin updated = pinRepository.save(pin);

        return mapToResponse(updated);
    }



    private PinResponse mapToResponse(Pin pin) {
        return PinResponse.builder()
                .id(pin.getId())
                .trackId(pin.getTrackId())
                .latitude(pin.getLatitude())
                .longitude(pin.getLongitude())
                .type(pin.getType())
                .name(pin.getName())
                .description(pin.getDescription())
                .build();
    }
}
