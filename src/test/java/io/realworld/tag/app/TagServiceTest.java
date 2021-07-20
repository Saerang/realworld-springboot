package io.realworld.tag.app;

import io.realworld.common.exception.TagNotFoundException;
import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TagServiceTest {

    @Autowired
    TagService tagService;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    EntityManager em;
    final static String TAG = "tag";

    @Test
    void createTag() {
        //given
        TagRequestDto dto = TagRequestDto.builder().tag(TAG).build();

        //when
        Tag tag = tagService.createTag(dto);

        em.flush();
        em.clear();

        Tag findTag = tagRepository.findByTag(TAG).orElseThrow(() -> new TagNotFoundException(TAG));

        //then
        assertThat(tag.getTag()).isEqualTo(findTag.getTag());
    }

    @Test
    void getTag() {
        //given
        TagRequestDto dto = TagRequestDto.builder().tag(TAG).build();

        //when
        Tag tag = tagService.getTag(dto);

        //then
        assertThat(tag.getTag()).isEqualTo(TAG);
    }

    @Test
    void getTagNotFound() {
        //given
        TagRequestDto dto = TagRequestDto.builder().tag("notFoundTag").build();

        //when

        //then
        assertThatThrownBy(() -> tagService.getTag(dto))
                .isInstanceOf(TagNotFoundException.class).hasMessage("Tag notFoundTag not found.");
    }
}
