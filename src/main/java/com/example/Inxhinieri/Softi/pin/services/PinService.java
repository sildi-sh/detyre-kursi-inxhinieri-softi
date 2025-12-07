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
