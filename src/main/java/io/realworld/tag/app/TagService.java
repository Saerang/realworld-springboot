package io.realworld.tag.app;

import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;

public interface TagService {
    Tag createTag(TagRequestDto dto);

    Tag getTag(TagRequestDto dto);
}
