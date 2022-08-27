package io.realworld.tag.app;

import io.realworld.common.exception.TagNotFoundException;
import io.realworld.tag.app.dto.TagRequestDto;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTagService implements TagService {

    private final TagRepository tagRepository;

    @Override
    @Transactional
    public Tag createTag(TagRequestDto dto) {
        Tag tag = Tag.builder().tag(dto.getTag()).build();

        Optional<Tag> optionalTag = tagRepository.findByTag(dto.getTag());

        return optionalTag.orElseGet(() -> tagRepository.save(tag));

    }

    @Override
    public Set<Tag> createTags(Set<TagRequestDto> dtos) {
        return dtos.stream()
                   .map(this::createTag)
                   .collect(Collectors.toSet());
    }

    @Override
    public Tag getTag(TagRequestDto dto) {
        return tagRepository.findByTag(dto.getTag()).orElseThrow(() -> new TagNotFoundException(dto.getTag()));
    }

    @Override
    public Optional<Tag> getTag(String tag) {
        return tagRepository.findByTag(tag);
    }

    @Override
    public Set<Tag> getTags() {
        return new HashSet<>(tagRepository.findAll());
    }

    @Override
    public Set<Tag> getTags(List<Long> tagIds) {
        return tagRepository.findAllByIdIn(tagIds);
    }

}
