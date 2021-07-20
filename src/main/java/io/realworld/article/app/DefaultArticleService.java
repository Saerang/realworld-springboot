package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleSearchDto;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.tag.app.TagService;
import io.realworld.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService{

    // TODO: 주입이 너무 많음 User, Follow 한번에 가져오는 중간 객체 있으면 좋을 듯.
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
    public List<Article> getArticles(ArticleSearchDto articleSearchDto, long userId) {
        return null;
    }


}
