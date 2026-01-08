package com.example.Inxhinieri.Softi.pin.controllers;

import com.example.Inxhinieri.Softi.pin.dto.PinRequest;
import com.example.Inxhinieri.Softi.pin.dto.PinResponse;
import com.example.Inxhinieri.Softi.pin.services.PinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pins")
public class PinController {

    private final PinService pinService;

    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @PostMapping
    public ResponseEntity<PinResponse> createPin(@RequestBody PinRequest request) {
        PinResponse response = pinService.krijoPin(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PinResponse> getPin(@PathVariable String id) {
        PinResponse response = pinService.getPinById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PinResponse> updatePin(
            @PathVariable String id,
            @RequestBody PinRequest request) {

        PinResponse response = pinService.updatePin(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePin(@PathVariable String id) {
        pinService.deletePin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<PinResponse>> getAllPins() {
        return ResponseEntity.ok(pinService.getAllPins());
    }
}
