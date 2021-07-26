package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.favorite.app.FavoriteServiceFactory;
import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;
import io.realworld.favorite.domain.FavoriteId;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import io.realworld.common.mapper.Mappers;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultArticleMapperService implements ArticleMapperService {
    final private ArticleService articleService;
    final private UserService userService;
    final private FollowRelationService followRelationService;
    final private FavoriteServiceFactory favoriteServiceFactory;

    @Override
    public SingleArticleResponseDto getSingleArticleResponseDto(String slug, Long userId) {
        Article article = articleService.getArticle(slug);
        User author = userService.getUserById(article.getUserId());
        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(article.getId());

        boolean following = false;
        boolean favorited = false;
        if (userId != null) {
            following = followRelationService.isFollowing(userId, article.getUserId());
            favorited = favoriteServiceFactory.getService(FavoriteType.ARTICLE).isFavorited(userId, article.getId());
        }

        return Mappers.toSingleArticleResponseDto(article, author, favorited, favorites.size(), following);
    }

    @Override
    public MultipleArticlesResponseDto getArticles(String tag, String author, String favorited, Pageable pageable, Long userId) {
        Page<Article> articles = articleService.getArticles(tag, author, favorited, pageable);

        if(articles.getTotalElements() == 0) {
            return MultipleArticlesResponseDto.builder().articlesCount(0).build();
        }

        List<Long> authorIds = articles.stream().map(Article::getUserId).collect(Collectors.toList());

        return Mappers.toMultipleArticlesResponseDto(articles, getUserMap(authorIds), getFavorites(articles), getFavoritedIds(userId), getFollower(userId));
    }

    // TODO: 넘 복잡. 이럴 땐 Entity 묶어서 한방쿼리로 가져오는게 좋을지? 아니면 repository 직접 호출해서 DTO 이용 하는게 좋을지?
    @Override
    public MultipleArticlesResponseDto getFeedArticles(Pageable pageable, Long userId) {
        List<Long> followerIds = getFollower(userId);

        Page<Article> articles = articleService.getArticlesByUserIds(followerIds, pageable);
        if(articles.getTotalElements() == 0) {
            return MultipleArticlesResponseDto.builder().articlesCount(0).build();
        }

        return Mappers.toMultipleArticlesResponseDto(articles, getUserMap(followerIds), getFavorites(articles), getFavoritedIds(userId), followerIds);
    }

    @Override
    public SingleArticleResponseDto createArticle(ArticleCreateDto articleCreateDto, Long currentUserId) {
        Article article = articleService.createArticle(articleCreateDto, currentUserId);
        User user = userService.getUserById(article.getUserId());

        return Mappers.toSingleArticleResponseDto(article, user, false, 0, false);
    }

    private List<Long> getFavoritedIds(Long userId) {
        if (userId == null) {
            return Collections.emptyList();
        }
        return favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavoritedIds(userId);
    }

    private Map<Long, User> getUserMap(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyMap();
        }

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
        if (userId == null) {
            return Collections.emptyList();
        }

        return followRelationService.getFollowRelations(userId).stream()
                .map(FollowRelation::getFollowRelationId)
                .map(FollowRelationId::getFollowerId)
                .collect(Collectors.toList());
    }

}
