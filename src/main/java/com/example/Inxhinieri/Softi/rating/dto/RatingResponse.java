package com.example.Inxhinieri.Softi.rating.dto;

import com.example.Inxhinieri.Softi.rating.enums.RatingType;

import java.time.LocalDateTime;

public class RatingResponse {

    private String id;
    private String userId;
    private RatingType targetType;
    private String targetId;
    private Integer score;
    private String comment;
    private LocalDateTime createdAt;

    public RatingResponse() {}

    public RatingResponse(String id, String userId, RatingType targetType, String targetId,
                          Integer score, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.score = score;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public RatingType getTargetType() { return targetType; }
    public void setTargetType(RatingType targetType) { this.targetType = targetType; }

    public String getTargetId() { return targetId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
