package io.realworld.article.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class MultipleArticleSearchDto {
    private final String tag;

    @JsonProperty("author")
    private final String authorUsername;

    @JsonProperty("author")
    private final String favoritedUsername;

    private final Pageable pageable;

    @Builder
    public MultipleArticleSearchDto(String tag, String authorUsername, String favoritedUsername, Pageable pageable) {
        this.tag = tag;
        this.authorUsername = authorUsername;
        this.favoritedUsername = favoritedUsername;
        this.pageable = pageable;
    }
}