package com.example.Inxhinieri.Softi;
import com.example.Inxhinieri.Softi.guide.dto.CreateGuideDto;
import com.example.Inxhinieri.Softi.guide.dto.GuideResponseDto;
import com.example.Inxhinieri.Softi.guide.dto.UpdateGuideDto;
import com.example.Inxhinieri.Softi.guide.model.Guide;
import com.example.Inxhinieri.Softi.guide.repository.GuideRepository;
import com.example.Inxhinieri.Softi.guide.services.GuideService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.Inxhinieri.Softi.track.enums.TrackDifficulty.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuideServiceTest {

    @Mock
    private GuideRepository guideRepository;

    @InjectMocks
    private GuideService guideService;

    @Test
    void create_shouldSaveGuideAndReturnResponseDto() {
        // Arrange: mock DTO (s'varet nga implementimi i CreateGuideDto)
        CreateGuideDto dto = mock(CreateGuideDto.class);
        when(dto.getTitle()).thenReturn("Hike Albanian Alps");
        when(dto.getDescription()).thenReturn("A 2-day hike");
        when(dto.getLocation()).thenReturn("Valbone");
        when(dto.getDifficulty()).thenReturn(Medium);
        when(dto.getBasePrice()).thenReturn(120.0);
        when(dto.getMaxParticipants()).thenReturn(10);
        when(dto.getUserId()).thenReturn("user-1");
        when(dto.getBusinessId()).thenReturn("biz-1");

        // save() kthen entity-n e ruajtur (me id)
        Guide saved = new Guide();
        saved.setId("g-1");
        saved.setTitle("Hike Albanian Alps");
        saved.setDescription("A 2-day hike");
        saved.setLocation("Valbone");
        saved.setDifficulty(Medium);
        saved.setBasePrice(120.0);
        saved.setMaxParticipants(10);
        saved.setUserId("user-1");
        saved.setBusinessId("biz-1");
        saved.setIsActive(true);

        ArgumentCaptor<Guide> captor = ArgumentCaptor.forClass(Guide.class);
        when(guideRepository.save(any(Guide.class))).thenReturn(saved);

        // Act
        GuideResponseDto res = guideService.create(dto);

        // Assert: response i map-uar
        assertNotNull(res);
        assertEquals("g-1", res.getId());
        assertEquals("Hike Albanian Alps", res.getTitle());
        assertEquals("A 2-day hike", res.getDescription());
        assertEquals("Valbone", res.getLocation());
        assertEquals(Medium, res.getDifficulty());
        assertEquals(120.0, res.getBasePrice());
        assertEquals(10, res.getMaxParticipants());
        assertTrue(res.getIsActive());

        // Assert: ruajtja u thirr me një Guide të ndërtuar nga dto
        verify(guideRepository).save(captor.capture());
        Guide toSave = captor.getValue();
        assertEquals("Hike Albanian Alps", toSave.getTitle());
        assertEquals("A 2-day hike", toSave.getDescription());
        assertEquals("Valbone", toSave.getLocation());
        assertEquals("MEDIUM", toSave.getDifficulty());
        assertEquals(120.0, toSave.getBasePrice());
        assertEquals(10, toSave.getMaxParticipants());
        assertEquals("user-1", toSave.getUserId());
        assertEquals("biz-1", toSave.getBusinessId());
        assertTrue(toSave.getIsActive());
    }

    @Test
    void findAll_shouldReturnMappedDtos() {
        // Arrange
        Guide g1 = new Guide();
        g1.setId("1");
        g1.setTitle("T1");
        g1.setDescription("D1");
        g1.setLocation("L1");
        g1.setDifficulty(Easy);
        g1.setBasePrice(50.0);
        g1.setMaxParticipants(5);
        g1.setIsActive(true);

        Guide g2 = new Guide();
        g2.setId("2");
        g2.setTitle("T2");
        g2.setDescription("D2");
        g2.setLocation("L2");
        g2.setDifficulty(Hard);
        g2.setBasePrice(200.0);
        g2.setMaxParticipants(12);
        g2.setIsActive(false);

        when(guideRepository.findAll()).thenReturn(List.of(g1, g2));

        // Act
        List<GuideResponseDto> res = guideService.findAll();

        // Assert
        assertEquals(2, res.size());
        assertEquals("1", res.get(0).getId());
        assertEquals("T1", res.get(0).getTitle());
        assertEquals("2", res.get(1).getId());
        assertEquals("T2", res.get(1).getTitle());

        verify(guideRepository).findAll();
    }

    @Test
    void update_shouldPatchOnlyNonNullFields() {
        // Arrange: existing guide
        Guide existing = new Guide();
        existing.setId("g-1");
        existing.setTitle("Old Title");
        existing.setDescription("Old Desc");
        existing.setLocation("Old Location");
        existing.setDifficulty(Easy);
        existing.setBasePrice(100.0);
        existing.setMaxParticipants(10);
        existing.setIsActive(true);

        when(guideRepository.findById("g-1")).thenReturn(Optional.of(existing));

        // dto: vetëm disa fusha jo-null (patch)
        UpdateGuideDto dto = mock(UpdateGuideDto.class);
        when(dto.getTitle()).thenReturn("New Title");
        when(dto.getDescription()).thenReturn(null); // nuk ndryshon
        when(dto.getDifficulty()).thenReturn(Hard);
        when(dto.getBasePrice()).thenReturn(150.0);
        when(dto.getIsActive()).thenReturn(false);

        // save() kthen objektin e njëjtë (të përditësuar)
        when(guideRepository.save(any(Guide.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        GuideResponseDto res = guideService.update("g-1", dto);

        // Assert: titulli u ndryshua, description mbeti e vjetër
        assertEquals("g-1", res.getId());
        assertEquals("New Title", res.getTitle());
        assertEquals("Old Desc", res.getDescription()); // patch nuk e ndryshoi
        assertEquals("Old Location", res.getLocation()); // service nuk e patch-on location fare
        assertEquals("HARD", res.getDifficulty());
        assertEquals(150.0, res.getBasePrice());
        assertFalse(res.getIsActive());

        verify(guideRepository).findById("g-1");
        verify(guideRepository).save(existing);
    }

    @Test
    void update_whenNotFound_shouldThrow() {
        when(guideRepository.findById("missing")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> guideService.update("missing", mock(UpdateGuideDto.class)));

        assertTrue(ex.getMessage().toLowerCase().contains("nuk u gjet"));
        verify(guideRepository).findById("missing");
        verify(guideRepository, never()).save(any());
    }

    @Test
    void delete_shouldCallRepositoryDeleteById() {
        // Act
        guideService.delete("g-1");

        // Assert
        verify(guideRepository).deleteById("g-1");
    }

    @Test
    void findByLocation_shouldFilterIgnoreCase() {
        // Arrange
        Guide a = new Guide();
        a.setId("1");
        a.setLocation("Valbone");
        a.setTitle("A");
        a.setDescription("d");
        a.setDifficulty(Easy);
        a.setBasePrice(10.0);
        a.setMaxParticipants(1);
        a.setIsActive(true);

        Guide b = new Guide();
        b.setId("2");
        b.setLocation("Theth");
        b.setTitle("B");
        b.setDescription("d");
        b.setDifficulty(Easy);
        b.setBasePrice(10.0);
        b.setMaxParticipants(1);
        b.setIsActive(true);

        when(guideRepository.findAll()).thenReturn(List.of(a, b));

        // Act
        List<GuideResponseDto> res = guideService.findByLocation("vALbOnE");

        // Assert
        assertEquals(1, res.size());
        assertEquals("1", res.get(0).getId());
        verify(guideRepository).findAll();
    }

    @Test
    void findByMaxPrice_shouldReturnOnlyGuidesWithPriceLessOrEqual() {
        // Arrange
        Guide cheap = new Guide();
        cheap.setId("1");
        cheap.setBasePrice(50.0);
        cheap.setTitle("Cheap");
        cheap.setDescription("d");
        cheap.setLocation("L");
        cheap.setDifficulty(Easy);
        cheap.setMaxParticipants(1);
        cheap.setIsActive(true);

        Guide expensive = new Guide();
        expensive.setId("2");
        expensive.setBasePrice(200.0);
        expensive.setTitle("Exp");
        expensive.setDescription("d");
        expensive.setLocation("L");
        expensive.setDifficulty(Easy);
        expensive.setMaxParticipants(1);
        expensive.setIsActive(true);

        when(guideRepository.findAll()).thenReturn(List.of(cheap, expensive));

        // Act
        List<GuideResponseDto> res = guideService.findByMaxPrice(100.0);

        // Assert
        assertEquals(1, res.size());
        assertEquals("1", res.get(0).getId());
        verify(guideRepository).findAll();
    }

    @Test
    void toggleStatus_shouldInvertIsActiveAndSave() {
        // Arrange
        Guide existing = new Guide();
        existing.setId("g-1");
        existing.setTitle("T");
        existing.setDescription("D");
        existing.setLocation("L");
        existing.setDifficulty(Easy);
        existing.setBasePrice(10.0);
        existing.setMaxParticipants(1);
        existing.setIsActive(true);

        when(guideRepository.findById("g-1")).thenReturn(Optional.of(existing));
        when(guideRepository.save(any(Guide.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        GuideResponseDto res = guideService.toggleStatus("g-1");

        // Assert
        assertFalse(res.getIsActive());
        verify(guideRepository).findById("g-1");
        verify(guideRepository).save(existing);
    }

    @Test
    void toggleStatus_whenNotFound_shouldThrow() {
        when(guideRepository.findById("missing")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> guideService.toggleStatus("missing"));

        assertTrue(ex.getMessage().toLowerCase().contains("nuk u gjet"));
        verify(guideRepository).findById("missing");
        verify(guideRepository, never()).save(any());
    }

    @Test
    void findById_shouldReturnGuide() {
        Guide g = new Guide();
        g.setId("g-1");

        when(guideRepository.findById("g-1")).thenReturn(Optional.of(g));

        Guide found = guideService.findById("g-1");

        assertNotNull(found);
        assertEquals("g-1", found.getId());
        verify(guideRepository).findById("g-1");
    }

    @Test
    void findById_whenNotFound_shouldThrow() {
        when(guideRepository.findById("missing")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> guideService.findById("missing"));

        assertTrue(ex.getMessage().toLowerCase().contains("id"));
        verify(guideRepository).findById("missing");
    }
}
