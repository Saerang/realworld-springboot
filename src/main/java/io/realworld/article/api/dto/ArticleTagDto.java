package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ArticleTagDto {

    private Long articleId;

    private String tag;

    @Builder
    public ArticleTagDto(Long articleId, String tag) {
        this.articleId = articleId;
        this.tag = tag;
    }

}
