package com.example.Inxhinieri.Softi;

import com.example.Inxhinieri.Softi.rating.dto.RatingRequest;
import com.example.Inxhinieri.Softi.rating.dto.RatingResponse;
import com.example.Inxhinieri.Softi.rating.enums.RatingType;
import com.example.Inxhinieri.Softi.rating.model.Rating;
import com.example.Inxhinieri.Softi.rating.repository.RatingRepository;
import com.example.Inxhinieri.Softi.rating.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    private Rating rating(String id, String userId, RatingType type, String targetId, Integer score, String comment) {
        Rating r = new Rating();
        r.setId(id);
        r.setUserId(userId);
        r.setTargetType(type);
        r.setTargetId(targetId);
        r.setScore(score);
        r.setComment(comment);

        // nëse Rating ka setCreatedAt, vendose; nëse jo, hiqe këtë pjesë
        try {
            r.setCreatedAt(LocalDateTime.from(Instant.now()));
        } catch (Exception ignored) {
            // nëse s'ekziston setter, s'na prish punë; toResponse thërret getCreatedAt()
        }
        return r;
    }

    private RatingRequest req(String userId, RatingType type, String targetId, Integer score, String comment) {
        RatingRequest req = new RatingRequest();
        req.setUserId(userId);
        req.setTargetType(type);
        req.setTargetId(targetId);
        req.setScore(score);
        req.setComment(comment);
        return req;
    }

    // ------------------ GET ALL ------------------

    @Test
    void getAll_shouldReturnMappedResponses() {
        when(ratingRepository.findAll()).thenReturn(List.of(
                rating("r1", "u1", RatingType.Guide, "t1", 5, "great"),
                rating("r2", "u2", RatingType.Guide, "t1", 3, "ok")
        ));

        List<RatingResponse> res = ratingService.getAll();

        assertEquals(2, res.size());
        assertEquals("r1", res.get(0).getId());
        assertEquals("u1", res.get(0).getUserId());
        assertEquals(5, res.get(0).getScore());
        verify(ratingRepository).findAll();
    }

    // ------------------ GET BY ID ------------------

    @Test
    void getById_whenFound_shouldReturnResponse() {
        Rating r = rating("r1", "u1", RatingType.Guide, "t1", 4, "nice");
        when(ratingRepository.findById("r1")).thenReturn(Optional.of(r));

        RatingResponse res = ratingService.getById("r1");

        assertNotNull(res);
        assertEquals("r1", res.getId());
        assertEquals("u1", res.getUserId());
        assertEquals(4, res.getScore());
        verify(ratingRepository).findById("r1");
    }

    @Test
    void getById_whenNotFound_shouldThrow() {
        when(ratingRepository.findById("missing")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.getById("missing"));

        assertTrue(ex.getMessage().contains("Rating not found"));
        verify(ratingRepository).findById("missing");
    }

    // ------------------ GET BY USER ID ------------------

    @Test
    void getByUserId_shouldCallRepositoryAndMap() {
        when(ratingRepository.findAllByUserId("u1")).thenReturn(List.of(
                rating("r1", "u1", RatingType.Guide, "t1", 5, null),
                rating("r2", "u1", RatingType.Business, "b1", 2, "bad")
        ));

        List<RatingResponse> res = ratingService.getByUserId("u1");

        assertEquals(2, res.size());
        assertEquals("u1", res.get(0).getUserId());
        assertEquals("u1", res.get(1).getUserId());
        verify(ratingRepository).findAllByUserId("u1");
    }

    // ------------------ GET BY TARGET ------------------

    @Test
    void getByTarget_shouldCallRepositoryAndMap() {
        when(ratingRepository.findAllByTargetTypeAndTargetId(RatingType.Guide, "t1"))
                .thenReturn(List.of(
                        rating("r1", "u1", RatingType.Guide, "t1", 4, "ok")
                ));

        List<RatingResponse> res = ratingService.getByTarget(RatingType.Guide, "t1");

        assertEquals(1, res.size());
        assertEquals("t1", res.get(0).getTargetId());
        assertEquals(RatingType.Guide, res.get(0).getTargetType());
        verify(ratingRepository).findAllByTargetTypeAndTargetId(RatingType.Guide, "t1");
    }

    // ------------------ CREATE ------------------

    @Test
    void create_whenValidAndNotDuplicate_shouldSaveAndReturnResponse() {
        RatingRequest request = req("u1", RatingType.Guide, "t1", 5, "great");

        when(ratingRepository.existsByUserIdAndTargetTypeAndTargetId("u1", RatingType.Guide, "t1"))
                .thenReturn(false);

        // save kthen entity-n që u ruajt
        ArgumentCaptor<Rating> captor = ArgumentCaptor.forClass(Rating.class);
        when(ratingRepository.save(any(Rating.class))).thenAnswer(inv -> inv.getArgument(0));

        RatingResponse res = ratingService.create(request);

        assertNotNull(res);
        assertNotNull(res.getId()); // UUID i gjeneruar
        assertEquals("u1", res.getUserId());
        assertEquals(RatingType.Guide, res.getTargetType());
        assertEquals("t1", res.getTargetId());
        assertEquals(5, res.getScore());
        assertEquals("great", res.getComment());

        verify(ratingRepository).existsByUserIdAndTargetTypeAndTargetId("u1", RatingType.Guide, "t1");
        verify(ratingRepository).save(captor.capture());

        Rating toSave = captor.getValue();
        assertNotNull(toSave.getId());
        assertEquals("u1", toSave.getUserId());
        assertEquals(RatingType.Guide, toSave.getTargetType());
        assertEquals("t1", toSave.getTargetId());
        assertEquals(5, toSave.getScore());
        assertEquals("great", toSave.getComment());
    }

    @Test
    void create_whenDuplicate_shouldThrowAndNotSave() {
        RatingRequest request = req("u1", RatingType.Guide, "t1", 4, null);

        when(ratingRepository.existsByUserIdAndTargetTypeAndTargetId("u1", RatingType.Guide, "t1"))
                .thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.create(request));

        assertTrue(ex.getMessage().toLowerCase().contains("already exists"));
        verify(ratingRepository).existsByUserIdAndTargetTypeAndTargetId("u1", RatingType.Guide, "t1");
        verify(ratingRepository, never()).save(any());
    }

    @Test
    void create_whenMissingUserId_shouldThrow() {
        RatingRequest request = req(" ", RatingType.Guide, "t1", 4, null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.create(request));

        assertTrue(ex.getMessage().toLowerCase().contains("userid is required"));
        verifyNoInteractions(ratingRepository);
    }

    @Test
    void create_whenMissingTargetType_shouldThrow() {
        RatingRequest request = req("u1", null, "t1", 4, null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.create(request));

        assertTrue(ex.getMessage().toLowerCase().contains("targettype is required"));
        verifyNoInteractions(ratingRepository);
    }

    @Test
    void create_whenMissingTargetId_shouldThrow() {
        RatingRequest request = req("u1", RatingType.Guide, " ", 4, null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.create(request));

        assertTrue(ex.getMessage().toLowerCase().contains("targetid is required"));
        verifyNoInteractions(ratingRepository);
    }

    @Test
    void create_whenMissingScore_shouldThrow() {
        RatingRequest request = req("u1", RatingType.Guide, "t1", null, null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.create(request));

        assertTrue(ex.getMessage().toLowerCase().contains("score is required"));
        verifyNoInteractions(ratingRepository);
    }

    @Test
    void create_whenScoreOutOfRange_shouldThrow() {
        RatingRequest request = req("u1", RatingType.Guide, "t1", 6, null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.create(request));

        assertTrue(ex.getMessage().toLowerCase().contains("between 1 and 5"));
        verifyNoInteractions(ratingRepository);
    }

    // ------------------ UPDATE ------------------

    @Test
    void update_whenFound_shouldUpdateScoreAndCommentIfProvided() {
        Rating existing = rating("r1", "u1", RatingType.Guide, "t1", 2, "old");
        when(ratingRepository.findById("r1")).thenReturn(Optional.of(existing));
        when(ratingRepository.save(any(Rating.class))).thenAnswer(inv -> inv.getArgument(0));

        RatingRequest patch = req(null, null, null, 5, "new comment");
        // vëre: update s’përdor userId/target fields, vetëm score/comment, kështu i lëmë null

        RatingResponse res = ratingService.update("r1", patch);

        assertEquals("r1", res.getId());
        assertEquals(5, res.getScore());
        assertEquals("new comment", res.getComment());

        verify(ratingRepository).findById("r1");
        verify(ratingRepository).save(existing);
    }

    @Test
    void update_whenScoreInvalid_shouldThrowAndNotSave() {
        Rating existing = rating("r1", "u1", RatingType.Guide, "t1", 2, "old");
        when(ratingRepository.findById("r1")).thenReturn(Optional.of(existing));

        RatingRequest patch = req(null, null, null, 0, null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.update("r1", patch));

        assertTrue(ex.getMessage().toLowerCase().contains("between 1 and 5"));
        verify(ratingRepository).findById("r1");
        verify(ratingRepository, never()).save(any());
    }

    @Test
    void update_whenNotFound_shouldThrow() {
        when(ratingRepository.findById("missing")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.update("missing", req(null, null, null, 5, null)));

        assertTrue(ex.getMessage().contains("Rating not found"));
        verify(ratingRepository).findById("missing");
        verify(ratingRepository, never()).save(any());
    }

    // ------------------ DELETE ------------------

    @Test
    void delete_whenExists_shouldDeleteById() {
        when(ratingRepository.existsById("r1")).thenReturn(true);

        ratingService.delete("r1");

        verify(ratingRepository).existsById("r1");
        verify(ratingRepository).deleteById("r1");
    }

    @Test
    void delete_whenNotExists_shouldThrowAndNotDelete() {
        when(ratingRepository.existsById("missing")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> ratingService.delete("missing"));

        assertTrue(ex.getMessage().contains("Rating not found"));
        verify(ratingRepository).existsById("missing");
        verify(ratingRepository, never()).deleteById(anyString());
    }
}
