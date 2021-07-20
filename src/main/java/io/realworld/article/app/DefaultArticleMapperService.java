package io.realworld.article.app;

import io.realworld.article.api.dto.*;
import io.realworld.article.domain.Article;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultArticleMapperService implements ArticleMapperService{
    final private ArticleService articleService;
    final private UserService userService;
    final private FollowRelationService followRelationService;

    @Override
    public SingleArticleResponseDto getArticle(SingleArticleSearchDto dto, long userId) {
        return null;
    }

    @Override
    public MultipleArticlesResponseDto getArticles(MultipleArticleSearchDto dto, long userId) {
        return null;
    }
}
