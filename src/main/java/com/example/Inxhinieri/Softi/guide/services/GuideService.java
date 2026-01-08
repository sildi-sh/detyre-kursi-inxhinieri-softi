package com.example.Inxhinieri.Softi.guide.services;

import com.example.Inxhinieri.Softi.guide.dto.CreateGuideDto;
import com.example.Inxhinieri.Softi.guide.dto.GuideResponseDto;
import com.example.Inxhinieri.Softi.guide.dto.UpdateGuideDto;
import com.example.Inxhinieri.Softi.guide.model.Guide;
import com.example.Inxhinieri.Softi.guide.repository.GuideRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuideService {

    private final GuideRepository guideRepository;

    // Konstruktori duhet të ketë saktësisht emrin e klasës: GuideServices
    public GuideService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    public GuideResponseDto create(CreateGuideDto dto) {
        Guide guide = new Guide();
        guide.setTitle(dto.getTitle());
        guide.setDescription(dto.getDescription());
        guide.setLocation(dto.getLocation());
        guide.setDifficulty(dto.getDifficulty());
        guide.setBasePrice(dto.getBasePrice());
        guide.setMaxParticipants(dto.getMaxParticipants());
        guide.setUserId(dto.getUserId());
        guide.setBusinessId(dto.getBusinessId());
        guide.setIsActive(true);

        Guide saved = guideRepository.save(guide);
        return mapToResponse(saved);
    }

    public List<GuideResponseDto> findAll() {
        return guideRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public GuideResponseDto update(String id, UpdateGuideDto dto) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guida nuk u gjet!"));

        if (dto.getTitle() != null) guide.setTitle(dto.getTitle());
        if (dto.getDescription() != null) guide.setDescription(dto.getDescription());
        if (dto.getDifficulty() != null) guide.setDifficulty(dto.getDifficulty());
        if (dto.getBasePrice() != null) guide.setBasePrice(dto.getBasePrice());
        if (dto.getIsActive() != null) guide.setIsActive(dto.getIsActive());

        Guide updated = guideRepository.save(guide);
        return mapToResponse(updated);
    }

    public void delete(String id) {
        guideRepository.deleteById(id);
    }

    private GuideResponseDto mapToResponse(Guide guide) {
        GuideResponseDto res = new GuideResponseDto();
        res.setId(guide.getId());
        res.setTitle(guide.getTitle());
        res.setDescription(guide.getDescription());
        res.setLocation(guide.getLocation());
        res.setDifficulty(guide.getDifficulty());
        res.setBasePrice(guide.getBasePrice());
        res.setMaxParticipants(guide.getMaxParticipants());
        res.setIsActive(guide.getIsActive());
        return res;
    }

    /* --- 1. KËRKIMI SIPAS VENDNDODHJES --- */
    public List<GuideResponseDto> findByLocation(String location) {
        return guideRepository.findAll().stream()
                .filter(g -> g.getLocation().equalsIgnoreCase(location))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /* --- 2. FILTRIMI SIPAS ÇMIMIT MAKSIMAL --- */
    public List<GuideResponseDto> findByMaxPrice(Double maxPrice) {
        return guideRepository.findAll().stream()
                .filter(g -> g.getBasePrice() <= maxPrice)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /* --- 3. NDRYSHIMI I STATUSIT (AKTIV/JOAKTIV) --- */
    public GuideResponseDto toggleStatus(String id) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guida nuk u gjet!"));

        // Nëse ishte true bëhet false, dhe anasjelltas
        guide.setIsActive(!guide.getIsActive());

        Guide updated = guideRepository.save(guide);
        return mapToResponse(updated);
    }
    // Shto këtë te GuideService.java
    public Guide findById(String id) {
        return guideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guida nuk u gjet me ID: " + id));
    }
}