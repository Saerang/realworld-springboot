package io.realworld.article.app.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleSearchCondition {
    private String tag;
    private String author;
    private String favorited;

    @Builder
    public ArticleSearchCondition(String tag, String author, String favorited) {
        this.tag = tag;
        this.author = author;
        this.favorited = favorited;
    }
}
