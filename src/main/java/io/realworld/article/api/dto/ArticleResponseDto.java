package io.realworld.article.api.dto;

import io.realworld.tag.app.dto.TagResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@ToString
public class ArticleResponseDto {

    private final String slug;

    private final String title;

    private final String description;

    private final String body;

    private final Set<TagResponseDto> tagList;

    private final boolean favorited;

    private final long favoritesCount;

    private final Author author;

    private final ZonedDateTime createdAt;

    private final ZonedDateTime updatedAt;

    @Builder
    public ArticleResponseDto(String slug, String title, String description, String body, Set<TagResponseDto> tagList, boolean favorited, long favoritesCount, Author author, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
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
    @ToString
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
