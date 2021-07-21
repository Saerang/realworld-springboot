package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.MultipleArticleSearchDto;
import io.realworld.article.api.dto.SingleArticleSearchDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.exception.ArticleNotFound;
import io.realworld.tag.app.TagService;
import io.realworld.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService{

    final private TagService tagService;
    final private ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleCreateDto dto, long userId) {
        Article article = Article.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .body(dto.getBody())
                .userId(userId)
                .build();

        if (dto.getTags() != null) {
            Set<Tag> tags = tagService.createTags(dto.getTags());
            article.addTags(tags);
        }

        return articleRepository.save(article);
    }

    @Override
    public Article getArticle(SingleArticleSearchDto dto) {
        return articleRepository.findBySlug(dto.getSlug()).orElseThrow(ArticleNotFound::new);
    }

    // TODO: queryDsl 로 바꿔 보기.
    @Override
    public List<Article> getArticlesFromSearchDto(MultipleArticleSearchDto articleSearchDto, long userId) {
        String tag = articleSearchDto.getTag();
        return articleRepository.findByTag(tag);
    }

}
