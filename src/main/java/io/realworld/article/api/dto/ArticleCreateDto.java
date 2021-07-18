package io.realworld.article.api.dto;

import io.realworld.article.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class ArticleCreateDto {
    private String title;
    private String description;
    private String body;
    private Set<Tag> tags;

    @Builder
    public ArticleCreateDto(String title, String description, String body, Set<Tag> tags) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
    }
}
