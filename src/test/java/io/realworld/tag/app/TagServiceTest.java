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
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TagServiceTest {

    @Autowired
    TagService tagService;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    EntityManager em;

    @Test
    void createTag() {
        //given
        TagRequestDto dto = TagRequestDto.builder().tag("tag").build();

        //when
        Tag tag = tagService.createTag(dto);

        em.flush();
        em.clear();

        Tag findTag = tagRepository.findByTag("tag").orElseThrow(() -> new TagNotFoundException("tag"));

        //then
        assertThat(tag.getTag()).isEqualTo(findTag.getTag());
    }

    @Test
    void createTag_alreadyTag() {
        //given
        Tag tag = Tag.builder().tag("tag").build();
        TagRequestDto dto = TagRequestDto.builder().tag("tag").build();
        Tag savedTag = tagRepository.save(tag);

        //when
        Tag createdTag = tagService.createTag(dto);

        em.flush();
        em.clear();

        //then
        assertThat(savedTag.getTag()).isEqualTo(createdTag.getTag());
        assertThat(savedTag.getId()).isEqualTo(createdTag.getId());
    }

    @Test
    void createTags_alreadyTag() {
        //given
        tagRepository.save(Tag.builder().tag("tag1").build());
        Set<TagRequestDto> dtos = Set.of(toTagRequestDto("tag1"), toTagRequestDto("tag2"), toTagRequestDto("tag3"));

        //when
        Set<Tag> tags = tagService.createTags(dtos);

        em.flush();
        em.clear();

        Set<Tag> findTags = tagRepository.findByTagIn(Set.of("tag1", "tag2", "tag3"));

        //then
        assertThat(tags).hasSize(3).extracting("tag").contains("tag1", "tag2", "tag3");
        assertThat(findTags).hasSize(3);
    }

    @Test
    void getTag() {
        //given
        tagRepository.save(Tag.builder().tag("tag").build());
        TagRequestDto dto = TagRequestDto.builder().tag("tag").build();

        //when
        Tag tag = tagService.getTag(dto);

        //then
        assertThat(tag.getTag()).isEqualTo("tag");
    }

    @Test
    void getTag_notFound() {
        //given
        TagRequestDto dto = TagRequestDto.builder().tag("notFoundTag").build();

        //when

        //then
        assertThatThrownBy(() -> tagService.getTag(dto))
                .isInstanceOf(TagNotFoundException.class).hasMessage("Tag notFoundTag not found.");
    }

    @Test
    void getTags() {
        //given
        String tag1 = "tag1";
        String tag2 = "tag2";
        Set<Tag> saveTags = Set.of(Tag.builder().tag(tag1).build(), Tag.builder().tag("tag2").build());
        tagRepository.saveAll(saveTags);

        //when
        Set<Tag> tags = tagService.getTags();

        //then
        assertThat(tags).extracting("tag").contains(tag1, tag2);
    }

    private TagRequestDto toTagRequestDto(String tag) {
        return TagRequestDto.builder().tag(tag).build();
    }
}
