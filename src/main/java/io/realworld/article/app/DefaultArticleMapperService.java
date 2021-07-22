package io.realworld.article.app;

import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.ArticleTag;
import io.realworld.article.domain.repository.ArticleTagRepository;
import io.realworld.favorite.app.FavoriteServiceFactory;
import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;
import io.realworld.favorite.domain.FavoriteId;
import io.realworld.tag.app.TagService;
import io.realworld.tag.domain.Tag;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultArticleMapperService implements ArticleMapperService {
    final private ArticleService articleService;
    final private ArticleTagService articleTagService;
    final private UserService userService;
    final private FollowRelationService followRelationService;
    final private FavoriteServiceFactory favoriteServiceFactory;

    @Override
    public SingleArticleResponseDto getSingleArticleResponseDto(String slug, long userId) {
        Article article = articleService.getArticle(slug);
        User user = userService.getUserById(userId);
        boolean following = followRelationService.isFollowing(userId, article.getId());
        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(article.getId());
        boolean favorited = favoriteServiceFactory.getService(FavoriteType.ARTICLE).isFavorited(userId, article.getId());

        return Mappers.toSingleArticleResponseDto(article, null, user, favorited, favorites.size(), following);
    }

    // TODO: 하나의 select 로 가져오는게 좋아보이는데 ..
    @Override
    public MultipleArticlesResponseDto getMultipleArticlesResponseDto(MultipleArticleSearchDto dto, long userId) {


//        return Mappers.toMultipleArticlesResponseDto(articles, userMap, );
        return null;
    }

    // TODO: 넘 복잡. 이럴 땐 Entity 묶어서 한방쿼리로 가져오는게 좋아보임. 아니면 repository 직접 호출해서 DTO 이용.
    @Override
    public MultipleArticlesResponseDto getMultipleArticlesResponseDtoFeed(Pageable pageable, long userId) {
        List<Long> followerIds = followRelationService.getFollowRelations(userId).stream()
                .map(FollowRelation::getFollowRelationId)
                .map(FollowRelationId::getFollowerId)
                .collect(Collectors.toList());

        Page<Article> articles = articleService.getArticlesByUserIds(followerIds, pageable);
        if(articles.getTotalElements() == 0) {
            return MultipleArticlesResponseDto.builder().count(0).build();
        }

        List<Long> articleIds = articles.stream().map(Article::getId).collect(Collectors.toList());
//        articleTagService.getArticleTagsByArticleIds(articleIds).stream().collect(Collectors.toMap(articleTag -> articleTag.getArticle().get))

        List<Favorite> favorites = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavorites(articleIds);
        Map<Long, Long> favoritesCount = favorites.stream().map(Favorite::getFavoriteId)
                .collect(Collectors.groupingBy(FavoriteId::getFavoritedId, Collectors.counting()));

        Map<Long, User> userMap = userService.getUsersByIds(followerIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));

        List<Long> favoritedIds = favoriteServiceFactory.getService(FavoriteType.ARTICLE).getFavoritedIds(userId);

        return Mappers.toMultipleArticlesResponseDto(articles, null, userMap, favoritesCount, favoritedIds, followerIds);
    }
}
