package com.example.Inxhinieri.Softi.rating.dto;

import com.example.Inxhinieri.Softi.rating.enums.RatingType;

public class RatingRequest {

    private String userId;
    private RatingType targetType;
    private String targetId;
    private Integer score;
    private String comment;

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
}
