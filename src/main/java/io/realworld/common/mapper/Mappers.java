package io.realworld.common.mapper;

import io.realworld.article.api.dto.ArticleResponseDto;
import io.realworld.article.api.dto.ArticleTagDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.ArticleTag;
import io.realworld.tag.app.dto.TagResponseDto;
import io.realworld.tag.app.dto.TagResponseDtos;
import io.realworld.tag.domain.Tag;
import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.api.dto.UserResponseDto;
import io.realworld.user.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static SingleArticleResponseDto toSingleArticleResponseDto(Article article, User user, boolean isFavorited, long favoritesCount, boolean isFollowing) {
        ArticleResponseDto articleResponseDto = toArticleResponseDto(
                article,
                article.getArticleTags().stream().map(ArticleTag::getTag).collect(Collectors.toSet()),
                user,
                isFavorited,
                favoritesCount,
                isFollowing
        );

        return SingleArticleResponseDto.builder().article(articleResponseDto).build();
    }

    public static MultipleArticlesResponseDto toMultipleArticlesResponseDto(Page<Article> articles, Map<Long, User> userMap, Map<Long, Long> favoritesCount, List<Long> favoritedIds, List<Long> followerIds) {
        List<ArticleResponseDto> articleResponseDtos = articles.stream()
                .map(article -> toArticleResponseDto(
                        article,
                        article.getArticleTags().stream().map(ArticleTag::getTag).collect(Collectors.toSet()),
                        userMap.get(article.getUserId()),
                        favoritedIds.contains(article.getId()),
                        favoritesCount.getOrDefault(article.getId(), 0L),
                        followerIds.contains(article.getUserId()))
                ).collect(Collectors.toList());

        return MultipleArticlesResponseDto.builder().articles(articleResponseDtos).articlesCount(articleResponseDtos.size()).build();
    }

    public static ArticleResponseDto toArticleResponseDto(Article article, Set<Tag> tags, User user, boolean isFavorited, long favoritesCount, boolean isFollowing) {
        ArticleResponseDto.Author author = ArticleResponseDto.Author.builder()
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .following(isFollowing)
                .build();

        return ArticleResponseDto.builder()
                .title(article.getTitle())
                .body(article.getBody())
                .description(article.getDescription())
                .tagList(tags.stream().map(Mappers::toTagResponseDtos).collect(Collectors.toSet()))
                .favorited(isFavorited)
                .favoritesCount(favoritesCount)
                .slug(article.getSlug())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .author(author)
                .build();
    }

    public static TagResponseDto toTagResponseDtos(Tag tag) {
        return TagResponseDto.builder().tag(tag.getTag()).build();
    }

    public static ArticleTagDto toArticleTagDto(ArticleTag articleTag) {
        return ArticleTagDto.builder()
                .articleId(articleTag.getArticle().getId())
                .tag(articleTag.getTag().getTag())
                .build();
    }

    public static TagResponseDto toTagResponseDto(Tag tag) {
        return TagResponseDto.builder()
                .tag(tag.getTag())
                .build();
    }

    public static TagResponseDtos toTagResponseDtos(Set<Tag> tags) {
        return TagResponseDtos.builder()
                .tags(tags.stream().map(Tag::getTag).collect(Collectors.toSet()))
                .build();
    }
}
