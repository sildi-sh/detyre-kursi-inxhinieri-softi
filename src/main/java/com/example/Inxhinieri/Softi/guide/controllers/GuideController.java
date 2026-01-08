package com.example.Inxhinieri.Softi.guide.controllers;

import com.example.Inxhinieri.Softi.guide.dto.CreateGuideDto;
import com.example.Inxhinieri.Softi.guide.dto.GuideResponseDto;
import com.example.Inxhinieri.Softi.guide.dto.UpdateGuideDto;
import com.example.Inxhinieri.Softi.guide.model.Guide;
import com.example.Inxhinieri.Softi.guide.services.GuideService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guides") // Kjo përcakton rrugën kryesore (p.sh. localhost:8080/guides)
public class GuideController {

    private final GuideService guideService;

    // Konstruktori që lidh Controller-in me Service-in
    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    /* --- KRIJIMI (POST) --- */
    @PostMapping
    public GuideResponseDto create(@Valid @RequestBody CreateGuideDto dto) {
        return guideService.create(dto);
    }

    /* --- LEXIMI I TË GJITHAVE (GET) --- */
    @GetMapping
    public List<GuideResponseDto> findAll() {
        return guideService.findAll();
    }

    /* --- LEXIMI SIPAS ID (GET) --- */
    @GetMapping("/{id}")
    public Guide findById(@PathVariable String id) {
        return guideService.findById(id);
    }

    /* --- NDRYSHIMI (PUT) --- */
    @PutMapping("/{id}")
    public GuideResponseDto update(@PathVariable String id, @RequestBody UpdateGuideDto dto) {
        return guideService.update(id, dto);
    }

    /* --- FSHIRJA (DELETE) --- */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        guideService.delete(id);
    }
}