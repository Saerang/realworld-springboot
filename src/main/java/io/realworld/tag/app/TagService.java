package io.realworld.tag.app;

import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TagService {
    Tag createTag(TagRequestDto dto);

    Set<Tag> createTags(Set<TagRequestDto> dto);

    Tag getTag(TagRequestDto dto);

    Optional<Tag> getTag(String tag);

    Set<Tag> getTags();

    Set<Tag> getTags(List<Long> tagIds);

}
