package com.example.Inxhinieri.Softi;

// 1. Import your project classes (since they are in sub-packages)
import com.example.Inxhinieri.Softi.media.services.MediaService;
import com.example.Inxhinieri.Softi.media.repository.MediaRepository;
import com.example.Inxhinieri.Softi.media.model.Media;
import com.example.Inxhinieri.Softi.media.dto.CreateMediaDto;
import com.example.Inxhinieri.Softi.media.dto.MediaResponseDto;


// 2. Import JUnit 5 and Mockito
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

// 3. Static imports for assertions and Mockito methods
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaService mediaService;

    private Media sampleMedia;
    private final String mediaId = "123";

    @BeforeEach
    void setUp() {
        sampleMedia = new Media();
        // If these set methods show red, ensure @Data or @Setter is on your Media model class
        sampleMedia.setId(mediaId);
        sampleMedia.setUserId("user-1");
        sampleMedia.setMediaUrl("http://example.com/audio.mp3");
        sampleMedia.setMediaType(Media.MediaType.Video);
    }

    @Test
    void create_ShouldReturnResponseDto_WhenSuccessful() {
        CreateMediaDto dto = new CreateMediaDto();
        dto.setUserId("user-1");
        dto.setMediaType(CreateMediaDto.MediaType.Video);
        dto.setMediaUrl("http://example.com/audio.mp3");

        when(mediaRepository.save(any(Media.class))).thenReturn(sampleMedia);

        MediaResponseDto result = mediaService.create(dto);

        assertNotNull(result);
        assertEquals(mediaId, result.getId());
        verify(mediaRepository, times(1)).save(any(Media.class));
    }

    @Test
    void findById_ShouldReturnDto_WhenIdExists() {
        when(mediaRepository.findById(mediaId)).thenReturn(Optional.of(sampleMedia));

        MediaResponseDto result = mediaService.findById(mediaId);

        assertNotNull(result);
        assertEquals(mediaId, result.getId());
    }

    @Test
    void delete_ShouldCallRepository_WhenIdExists() {
        when(mediaRepository.existsById(mediaId)).thenReturn(true);

        mediaService.delete(mediaId);

        verify(mediaRepository, times(1)).deleteById(mediaId);
    }
}