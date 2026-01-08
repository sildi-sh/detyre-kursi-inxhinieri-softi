package com.example.Inxhinieri.Softi.rating.repository;

import com.example.Inxhinieri.Softi.rating.enums.RatingType;
import com.example.Inxhinieri.Softi.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {

    List<Rating> findAllByUserId(String userId);

    List<Rating> findAllByTargetTypeAndTargetId(RatingType targetType, String targetId);

    boolean existsByUserIdAndTargetTypeAndTargetId(String userId, RatingType targetType, String targetId);
}
