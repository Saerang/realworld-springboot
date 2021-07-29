package io.realworld.article.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.realworld.tag.app.dto.TagRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class ArticleUpdateDto {
    @NotNull
    @JsonProperty("article")
    private ArticleDto articleDto;

    @Builder
    public ArticleUpdateDto(ArticleDto articleDto) {
        this.articleDto = articleDto;
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class ArticleDto {
        private String title;
        private String description;
        private String body;
        private Set<TagRequestDto> tags;

        @Builder
        public ArticleDto(String title, String description, String body, Set<TagRequestDto> tags) {
            this.title = title;
            this.description = description;
            this.body = body;
            this.tags = tags;
        }
    }
}
