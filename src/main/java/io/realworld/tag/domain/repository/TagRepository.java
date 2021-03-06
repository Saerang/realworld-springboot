package io.realworld.tag.domain.repository;

import io.realworld.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTag(String tag);

    Set<Tag> findByTagIn(Set<String> tag);

    Set<Tag> findAllByIdIn(List<Long> tagIds);
}
