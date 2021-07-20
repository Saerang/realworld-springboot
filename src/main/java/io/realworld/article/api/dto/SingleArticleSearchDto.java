package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SingleArticleSearchDto {
    private final String slug;

    @Builder
    public SingleArticleSearchDto(String slug) {
        this.slug = slug;
    }
}
