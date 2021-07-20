package io.realworld.tag.app;

import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceMockTest {

    @Mock
    TagRepository tagRepository;

    TagService tagService;

    final static String TAG = "tag";

    @BeforeEach
    void setUp() {
        tagService = new DefaultTagService(tagRepository);
    }

    @Test
    void createTag() {
        //given
        Tag tag = Tag.builder().tag(TAG).build();
        TagRequestDto dto = TagRequestDto.builder().tag(TAG).build();
        when(tagRepository.save(tag)).thenReturn(tag);

        //when
        tagService.createTag(dto);

        //then
        verify(tagRepository).save(any());
    }

    @Test
    void getTag() {
        //given
        Tag tag = Tag.builder().tag(TAG).build();
        TagRequestDto dto = TagRequestDto.builder().tag(TAG).build();
        when(tagRepository.findByTag(tag.getTag())).thenReturn(Optional.of(tag));

        //when
        tagService.getTag(dto);

        //then
        verify(tagRepository).findByTag(any());
    }
}
