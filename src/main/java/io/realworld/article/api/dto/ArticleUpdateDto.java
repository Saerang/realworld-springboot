package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ArticleUpdateDto {
    private String title;
    private String description;
    private String body;

    @Builder
    public ArticleUpdateDto(String title, String description, String body) {
        this.title = title;
        this.description = description;
        this.body = body;
    }
}
