package io.realworld.tag.api;

import io.realworld.common.mapper.Mappers;
import io.realworld.tag.app.TagService;
import io.realworld.tag.app.dto.TagResponseDtos;
import io.realworld.tag.domain.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class TagController {

    final private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public TagResponseDtos getTags() {
        Set<Tag> tags = tagService.getTags();
        return Mappers.toTagResponseDtos(tags);
    }
}
