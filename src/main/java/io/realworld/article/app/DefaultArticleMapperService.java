package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultArticleMapperService implements ArticleMapperService{
    final private ArticleService articleService;
    final private UserService userService;
    final private FollowRelationService followRelationService;


    @Override
    public ArticleResponseDto getArticle(ArticleCreateDto dto) {
        return null;
    }
}
