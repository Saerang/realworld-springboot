package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.ArticleTag;
import io.realworld.common.mapper.Mappers;
import io.realworld.favorite.app.FavoriteServiceFactory;
import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;
import io.realworld.favorite.domain.FavoriteId;
import io.realworld.tag.app.TagService;
import io.realworld.tag.domain.Tag;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.ProfileService;
import io.realworld.user.app.UserService;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultArticleMapperService implements ArticleMapperService, ArticleFavoriteMapperService {

    final private ArticleService articleService;
    final private TagService tagService;
    final private ProfileService profileService;
    final private UserService userService;
    final private FollowRelationService followRelationService;
    final private FavoriteServiceFactory favoriteServiceFactory;

    @Override
    public SingleArticleResponseDto getArticle(String slug, Long userId) {
        Article article = articleService.getArticle(slug);

        List<Long> tagIds = article.getArticleTags().stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        Set<Tag> tags = tagService.getTags(tagIds);

        Pair<User, Boolean> profile = profileService.getProfile(article.getUserId(), userId);

        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(article.getId());

        boolean favorited = false;
        if (userId != null) {
            List<Long> favoriteUserIds = favorites.stream().map(Favorite::getFavoriteId).map(FavoriteId::getUserId).collect(Collectors.toList());
            favorited = favoriteUserIds.contains(userId);
        }

        return Mappers.toSingleArticleResponseDto(article, tags, profile.getKey(), favorited, favorites.size(), profile.getValue());
    }

    @Override
    public MultipleArticlesResponseDto getArticles(String tag, String author, String favorited, Long userId, Pageable pageable) {
        Page<Article> articles = articleService.getArticles(tag, author, favorited, pageable);

        if (articles.getTotalElements() == 0) {
            return MultipleArticlesResponseDto.builder().articlesCount(0).build();
        }

        List<Long> authorIds = articles.stream().map(Article::getUserId).collect(Collectors.toList());

        return Mappers.toMultipleArticlesResponseDto(articles, getTagsMap(articles), getUserMap(authorIds), getFavorites(articles), getFavoritedIds(userId), getFollower(userId));
    }

    @Override
    public MultipleArticlesResponseDto getFeedArticles(Long userId, Pageable pageable) {
        List<Long> followerIds = getFollower(userId);

        Page<Article> articles = articleService.getArticlesByUserIds(followerIds, pageable);

        if (articles.getTotalElements() == 0) {
            return MultipleArticlesResponseDto.builder().articlesCount(0).build();
        }

        return Mappers.toMultipleArticlesResponseDto(articles, getTagsMap(articles), getUserMap(followerIds), getFavorites(articles), getFavoritedIds(userId), followerIds);
    }

    @Override
    public SingleArticleResponseDto createArticle(ArticleCreateDto articleCreateDto, Long currentUserId) {
        Article article = articleService.createArticle(articleCreateDto, currentUserId);

        List<Long> tagIds = article.getArticleTags().stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        Set<Tag> tags = tagService.getTags(tagIds);

        Pair<User, Boolean> profile = profileService.getProfile(currentUserId, null);

        return Mappers.toSingleArticleResponseDto(article, tags, profile.getKey(), false, 0, false);
    }

    @Override
    public SingleArticleResponseDto updateArticle(ArticleUpdateDto dto, String slug, Long currentUserId) {
        Article article = articleService.updateArticle(dto, slug, currentUserId);

        List<Long> tagIds = article.getArticleTags().stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        Set<Tag> tags = tagService.getTags(tagIds);

        Pair<User, Boolean> profile = profileService.getProfile(currentUserId, null);

        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(article.getId());
        List<Long> favoriteUserIds = favorites.stream().map(Favorite::getFavoriteId).map(FavoriteId::getUserId).collect(Collectors.toList());
        boolean favorited = favoriteUserIds.contains(currentUserId);

        return Mappers.toSingleArticleResponseDto(article, tags, profile.getKey(), favorited, favorites.size(), false);
    }

    @Override
    public void deleteArticle(String slug, Long userId) {
        articleService.deleteArticle(slug, userId);
    }

    @Override
    public SingleArticleResponseDto favoriteArticle(String slug, Long currentUserId) {
        Article article = articleService.getArticle(slug);

        List<Long> tagIds = article.getArticleTags().stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        Set<Tag> tags = tagService.getTags(tagIds);

        Pair<User, Boolean> profile = profileService.getProfile(article.getUserId(), currentUserId);

        favoriteServiceFactory.getService(FavoriteType.ARTICLE).favoriteAuthor(currentUserId, article.getId());

        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(article.getId());

        return Mappers.toSingleArticleResponseDto(article, tags, profile.getKey(), true, favorites.size(), profile.getRight());
    }

    @Override
    public SingleArticleResponseDto unfavoriteArticle(String slug, Long currentUserId) {
        Article article = articleService.getArticle(slug);

        List<Long> tagIds = article.getArticleTags().stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        Set<Tag> tags = tagService.getTags(tagIds);

        Pair<User, Boolean> profile = profileService.getProfile(article.getUserId(), currentUserId);

        favoriteServiceFactory.getService(FavoriteType.ARTICLE).unfavoriteAuthor(currentUserId, article.getId());

        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(article.getId());

        return Mappers.toSingleArticleResponseDto(article, tags, profile.getKey(), false, favorites.size(), profile.getValue());
    }

    private Map<Long, Set<Tag>> getTagsMap(Page<Article> articles) {
        Map<Long, Set<Tag>> tagsMap = new HashMap<>();
        articles.forEach(article -> {
            List<Long> tagIds = article.getArticleTags().stream().map(ArticleTag::getTagId).collect(Collectors.toList());
            Set<Tag> tags = tagService.getTags(tagIds);

            tagsMap.put(article.getId(), tags);
        });
        return tagsMap;
    }

    private List<Long> getFavoritedIds(Long userId) {
        return favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavoritedIds(userId);
    }

    private Map<Long, User> getUserMap(List<Long> userIds) {
        return userService.getUsersByIds(userIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));
    }

    private Map<Long, Long> getFavorites(Page<Article> articles) {
        if (articles.isEmpty()) {
            return Collections.emptyMap();
        }

        List<Long> articleIds = articles.stream().map(Article::getId).collect(Collectors.toList());
        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(articleIds);

        return favorites.stream().map(Favorite::getFavoriteId)
                .collect(Collectors.groupingBy(FavoriteId::getFavoritedId, Collectors.counting()));
    }

    private List<Long> getFollower(Long userId) {
        return followRelationService.getFollowRelations(userId).stream()
                .map(FollowRelation::getFollowRelationId)
                .map(FollowRelationId::getFollowerId)
                .collect(Collectors.toList());
    }

}
