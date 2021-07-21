package io.realworld.article.app;

import io.realworld.article.api.dto.*;
import io.realworld.article.domain.Article;
import io.realworld.favourite.app.FavouriteServiceFactory;
import io.realworld.favourite.domain.Favourite;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultArticleMapperService implements ArticleMapperService{
    final private ArticleService articleService;
    final private UserService userService;
    final private FollowRelationService followRelationService;
    final private FavouriteServiceFactory favouriteServiceFactory;

    @Override
    public SingleArticleResponseDto getSingleArticleResponseDto(SingleArticleSearchDto dto, long userId) {
        Article article = articleService.getArticle(dto);
        User user = userService.getUserById(userId);
        boolean following = followRelationService.isFollowing(userId, article.getId());


        return Mappers.toSingleArticleResponseDto(article, user, following);
    }

    @Override
    public MultipleArticlesResponseDto getMultipleArticleResponseDto(MultipleArticleSearchDto dto, long userId) {
        return null;
    }
}
