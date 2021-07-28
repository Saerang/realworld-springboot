package io.realworld.favorite.app;

import io.realworld.article.app.ArticleService;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import org.springframework.stereotype.Service;

@Service
public class ArticleFavoriteMapperService implements FavoriteMapper{
    final private ArticleService articleService;
    final private UserService userService;
    final private FollowRelationService followRelationService;
    final private FavoriteServiceFactory favoriteServiceFactory;

    public ArticleFavoriteMapperService(ArticleService articleService, UserService userService, FollowRelationService followRelationService, FavoriteServiceFactory favoriteServiceFactory) {
        this.articleService = articleService;
        this.userService = userService;
        this.followRelationService = followRelationService;
        this.favoriteServiceFactory = favoriteServiceFactory;
    }
}
