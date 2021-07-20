package io.realworld.tag.app;

import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;

import java.util.Set;

public interface TagService {
    Tag createTag(TagRequestDto dto);

    Set<Tag> createTags(Set<TagRequestDto> dto);

    Tag getTag(TagRequestDto dto);
}