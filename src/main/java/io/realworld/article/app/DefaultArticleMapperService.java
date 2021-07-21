package io.realworld.article.app;

import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.api.dto.SingleArticleSearchDto;
import io.realworld.article.domain.Article;
import io.realworld.favorite.app.FavoriteServiceFactory;
import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultArticleMapperService implements ArticleMapperService{
    final private ArticleService articleService;
    final private UserService userService;
    final private FollowRelationService followRelationService;
    final private FavoriteServiceFactory favoriteServiceFactory;

    @Override
    public SingleArticleResponseDto getSingleArticleResponseDto(SingleArticleSearchDto dto, long userId) {
        Article article = articleService.getArticle(dto);
        User user = userService.getUserById(userId);
        boolean following = followRelationService.isFollowing(userId, article.getId());
        boolean favorited = favoriteServiceFactory.getService(FavoriteType.ARTICLE).isFavorited(userId, article.getId());

        return Mappers.toSingleArticleResponseDto(article, user, favorited, 1, following);
    }

    @Override
    public MultipleArticlesResponseDto getMultipleArticleResponseDto(MultipleArticleSearchDto dto, long userId) {
        return null;
    }
}
