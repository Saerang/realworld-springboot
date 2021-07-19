package io.realworld.article.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class ArticleResponseDto {

    private final String slug;
    private final String title;
    private final String description;
    private final String body;
    private final Set<TagResponseDto> tagList;
    private final boolean favorited;
    private final int favoritesCount;
    private final Author author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public ArticleResponseDto(String slug, String title, String description, String body, Set<TagResponseDto> tagList, boolean favorited, int favoritesCount, Author author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Getter
    public static class Author {
        private final String username;
        private final String bio;
        private final String image;
        private final boolean following;

        @Builder
        public Author(String username, String bio, String image, boolean following) {
            this.username = username;
            this.bio = bio;
            this.image = image;
            this.following = following;
        }
    }
}