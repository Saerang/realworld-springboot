package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleResponseDto;
import io.realworld.user.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService{

    final private ArticleRepository articleRepository;

    @Override
    public ArticleResponseDto createArticle(ArticleCreateDto article) {
        return null;
    }
}
