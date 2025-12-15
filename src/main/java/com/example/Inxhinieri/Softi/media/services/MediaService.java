package com.example.Inxhinieri.Softi.media.services;

import com.example.Inxhinieri.Softi.media.dto.CreateMediaDto;
import com.example.Inxhinieri.Softi.media.dto.MediaResponseDto;
import com.example.Inxhinieri.Softi.media.dto.UpdateMediaDto;
import com.example.Inxhinieri.Softi.media.model.Media;
import com.example.Inxhinieri.Softi.media.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    /* ---------------- CREATE ---------------- */

    public MediaResponseDto create(CreateMediaDto dto) {
        Media media = new Media();
        media.setUserId(dto.getUserId());
        media.setTrackId(dto.getTrackId());
        media.setGuideId(dto.getGuideId());
        media.setMediaUrl(dto.getMediaUrl());
        media.setMediaType(Media.MediaType.valueOf(dto.getMediaType().name()));

        Media saved = mediaRepository.save(media);
        return toResponseDto(saved);
    }

    /* ---------------- READ ---------------- */

    public List<MediaResponseDto> findAll() {
        return mediaRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public MediaResponseDto findById(String id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        return toResponseDto(media);
    }

    /* ---------------- UPDATE ---------------- */

    public MediaResponseDto update(String id, UpdateMediaDto dto) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        if (dto.getMediaUrl() != null) {
            media.setMediaUrl(dto.getMediaUrl());
        }
        if (dto.getMediaType() != null) {
            media.setMediaType(Media.MediaType.valueOf(dto.getMediaType().name()));
        }
        if (dto.getTrackId() != null) {
            media.setTrackId(dto.getTrackId());
        }
        if (dto.getGuideId() != null) {
            media.setGuideId(dto.getGuideId());
        }

        Media updated = mediaRepository.save(media);
        return toResponseDto(updated);
    }

    /* ---------------- DELETE ---------------- */

    public void delete(String id) {
        if (!mediaRepository.existsById(id)) {
            throw new RuntimeException("Media not found");
        }
        mediaRepository.deleteById(id);
    }

    /* ---------------- MAPPER ---------------- */

    private MediaResponseDto toResponseDto(Media media) {
        MediaResponseDto dto = new MediaResponseDto();
        dto.setId(media.getId());
        dto.setUserId(media.getUserId());
        dto.setTrackId(media.getTrackId());
        dto.setGuideId(media.getGuideId());
        dto.setMediaUrl(media.getMediaUrl());
        dto.setMediaType(MediaResponseDto.MediaType.valueOf(media.getMediaType().name())); // fixed
        dto.setCreatedAt(media.getCreatedAt());
        return dto;
    }
}
