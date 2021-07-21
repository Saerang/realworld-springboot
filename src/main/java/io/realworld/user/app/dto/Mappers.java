package io.realworld.user.app.dto;

import io.realworld.article.api.dto.ArticleResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.domain.User;
import org.springframework.util.Assert;

public class Mappers {
    public static UserResponseDto toUserCreateResponseDto(User user, String token) {
        UserResponseDto.UserDto userDto = UserResponseDto.UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .token(token)
                .build();

        return UserResponseDto.builder().user(userDto).build();
    }

    public static ProfileResponseDto toProfileResponseDto(User user, Boolean isFollowing) {
        ProfileResponseDto.ProfileDto profileDto = ProfileResponseDto.ProfileDto.builder()
                .bio(user.getBio())
                .username(user.getUsername())
                .image(user.getImage())
                .following(isFollowing)
                .build();

        return ProfileResponseDto.builder().profile(profileDto).build();
    }

    public static SingleArticleResponseDto toSingleArticleResponseDto(Article article, User user, boolean isFavorited, int favoritesCount, boolean isFollowing) {
        ArticleResponseDto.Author author = ArticleResponseDto.Author.builder()
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .following(isFollowing)
                .build();

        ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                .title(article.getTitle())
                .body(article.getBody())
                .description(article.getDescription())
                .favorited(isFavorited)
                .favoritesCount(favoritesCount)
                .slug(article.getSlug())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .author(author)
                .build();

        return SingleArticleResponseDto.builder().article(articleResponseDto).build();
    }

}
