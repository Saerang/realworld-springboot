package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SingleArticleResponseDto {
    private final ArticleResponseDto article;

    @Builder
    public SingleArticleResponseDto(ArticleResponseDto article) {
        this.article = article;
    }
}
