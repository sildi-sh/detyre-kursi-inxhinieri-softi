package com.example.Inxhinieri.Softi.pin.controllers;

import com.example.Inxhinieri.Softi.pin.dto.PinRequest;
import com.example.Inxhinieri.Softi.pin.dto.PinResponse;
import com.example.Inxhinieri.Softi.pin.services.PinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pins")
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
}
