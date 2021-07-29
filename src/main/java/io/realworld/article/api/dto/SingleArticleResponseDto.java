package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SingleArticleResponseDto {
    // TODO: Single, Multiple 같이 쓰는데 분리하는게 좋음.
    private final ArticleResponseDto article;

    @Builder
    public SingleArticleResponseDto(ArticleResponseDto article) {
        this.article = article;
    }
}
