package com.example.Inxhinieri.Softi.rating.services;

import com.example.Inxhinieri.Softi.rating.dto.RatingRequest;
import com.example.Inxhinieri.Softi.rating.dto.RatingResponse;
import com.example.Inxhinieri.Softi.rating.enums.RatingType;
import com.example.Inxhinieri.Softi.rating.model.Rating;
import com.example.Inxhinieri.Softi.rating.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<RatingResponse> getAll() {
        return ratingRepository.findAll().stream().map(this::toResponse).toList();
    }

    public RatingResponse getById(String id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        return toResponse(rating);
    }

    public List<RatingResponse> getByUserId(String userId) {
        return ratingRepository.findAllByUserId(userId).stream().map(this::toResponse).toList();
    }

    public List<RatingResponse> getByTarget(RatingType targetType, String targetId) {
        return ratingRepository.findAllByTargetTypeAndTargetId(targetType, targetId)
                .stream().map(this::toResponse).toList();
    }

    public RatingResponse create(RatingRequest req) {
        validateCreate(req);

        // opsionale: mos lejo 2 rating për të njëjtin user+target
        if (ratingRepository.existsByUserIdAndTargetTypeAndTargetId(req.getUserId(), req.getTargetType(), req.getTargetId())) {
            throw new RuntimeException("Rating already exists for this user and target");
        }

        Rating rating = new Rating();
        rating.setId(UUID.randomUUID().toString());
        rating.setUserId(req.getUserId());
        rating.setTargetType(req.getTargetType());
        rating.setTargetId(req.getTargetId());
        rating.setScore(req.getScore());
        rating.setComment(req.getComment());

        return toResponse(ratingRepository.save(rating));
    }

    public RatingResponse update(String id, RatingRequest req) {
        Rating existing = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));

        // zakonisht update bëjmë vetëm score/comment
        if (req.getScore() != null) {
            if (req.getScore() < 1 || req.getScore() > 5) {
                throw new RuntimeException("Score must be between 1 and 5");
            }
            existing.setScore(req.getScore());
        }

        if (req.getComment() != null) {
            existing.setComment(req.getComment());
        }

        return toResponse(ratingRepository.save(existing));
    }

    public void delete(String id) {
        if (!ratingRepository.existsById(id)) {
            throw new RuntimeException("Rating not found with id: " + id);
        }
        ratingRepository.deleteById(id);
    }

    private void validateCreate(RatingRequest req) {
        if (req.getUserId() == null || req.getUserId().isBlank()) {
            throw new RuntimeException("userId is required");
        }
        if (req.getTargetType() == null) {
            throw new RuntimeException("targetType is required");
        }
        if (req.getTargetId() == null || req.getTargetId().isBlank()) {
            throw new RuntimeException("targetId is required");
        }
        if (req.getScore() == null) {
            throw new RuntimeException("score is required");
        }
        if (req.getScore() < 1 || req.getScore() > 5) {
            throw new RuntimeException("Score must be between 1 and 5");
        }
    }

    private RatingResponse toResponse(Rating r) {
        return new RatingResponse(
                r.getId(),
                r.getUserId(),
                r.getTargetType(),
                r.getTargetId(),
                r.getScore(),
                r.getComment(),
                r.getCreatedAt()
        );
    }
}
