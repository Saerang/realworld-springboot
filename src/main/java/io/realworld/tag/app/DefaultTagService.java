package io.realworld.tag.app;

import io.realworld.common.exception.TagNotFoundException;
import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTagService implements TagService{

    final private TagRepository tagRepository;

    @Override
    public Tag createTag(TagRequestDto dto) {
        Tag tag = Tag.builder().tag(dto.getTag()).build();

        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(TagRequestDto dto) {
        return tagRepository.findByTag(dto.getTag()).orElseThrow(() -> new TagNotFoundException(dto.getTag()));
    }
}