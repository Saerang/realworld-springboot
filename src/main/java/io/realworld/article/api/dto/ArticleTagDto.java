package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleTagDto {
    private final Long articleId;
    private final String tag;

    @Builder
    public ArticleTagDto(Long articleId, String tag) {
        this.articleId = articleId;
        this.tag = tag;
    }

}
