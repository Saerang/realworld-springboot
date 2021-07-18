package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.domain.Article;
import io.realworld.user.app.UserService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.repository.ArticleRepository;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService{

    final private UserService userService;
    final private FollowRelationRepository followRelationRepository;
    final private ArticleRepository articleRepository;

    @Override
    public SingleArticleResponseDto createArticle(ArticleCreateDto dto) {

        Article article = Article.builder().title("title").description("description").body("body").build();
        return Mappers.toSingleArticleResponseDto(article, null, false);
    }
}
