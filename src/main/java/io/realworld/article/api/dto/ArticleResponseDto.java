package io.realworld.article.api.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
public class ArticleResponseDto {
    ArticleDto article;

    public static class ArticleDto {
        private String slug;
        private String title;
        private String description;
        private String body;
        private Set<TagResponseDto> tagList;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private boolean favorited;
        private int favoritesCount;

    }
}
