package com.example.Inxhinieri.Softi.media.controllers;

import com.example.Inxhinieri.Softi.media.dto.CreateMediaDto;
import com.example.Inxhinieri.Softi.media.dto.MediaResponseDto;
import com.example.Inxhinieri.Softi.media.dto.UpdateMediaDto;
import com.example.Inxhinieri.Softi.media.services.MediaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /* ---------------- CREATE ---------------- */

    @PostMapping
    public MediaResponseDto create(@Valid @RequestBody CreateMediaDto dto) {
        return mediaService.create(dto);
    }

    /* ---------------- READ ---------------- */

    @GetMapping
    public List<MediaResponseDto> findAll() {
        return mediaService.findAll();
    }

    @GetMapping("/{id}")
    public MediaResponseDto findById(@PathVariable String id) {
        return mediaService.findById(id);
    }

    /* ---------------- UPDATE ---------------- */

    @PutMapping("/{id}")
    public MediaResponseDto update(
            @PathVariable String id,
            @RequestBody UpdateMediaDto dto
    ) {
        return mediaService.update(id, dto);
    }

    /* ---------------- DELETE ---------------- */

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        mediaService.delete(id);
    }
}
