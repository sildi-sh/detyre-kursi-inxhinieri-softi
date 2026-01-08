package com.example.Inxhinieri.Softi.rating.controllers;

import com.example.Inxhinieri.Softi.rating.dto.RatingRequest;
import com.example.Inxhinieri.Softi.rating.dto.RatingResponse;
import com.example.Inxhinieri.Softi.rating.enums.RatingType;
import com.example.Inxhinieri.Softi.rating.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // GET /ratings
    @GetMapping
    public ResponseEntity<List<RatingResponse>> getAll() {
        return ResponseEntity.ok(ratingService.getAll());
    }

    // GET /ratings/{id}
    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(ratingService.getById(id));
    }

    // GET /ratings/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingResponse>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getByUserId(userId));
    }

    // GET /ratings/target/{targetType}/{targetId}
    @GetMapping("/target/{targetType}/{targetId}")
    public ResponseEntity<List<RatingResponse>> getByTarget(
            @PathVariable RatingType targetType,
            @PathVariable String targetId
    ) {
        return ResponseEntity.ok(ratingService.getByTarget(targetType, targetId));
    }

    // POST /ratings
    @PostMapping
    public ResponseEntity<RatingResponse> create(@RequestBody RatingRequest request) {
        RatingResponse created = ratingService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /ratings/{id}
    @PutMapping("/{id}")
    public ResponseEntity<RatingResponse> update(@PathVariable String id, @RequestBody RatingRequest request) {
        return ResponseEntity.ok(ratingService.update(id, request));
    }

    // DELETE /ratings/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
