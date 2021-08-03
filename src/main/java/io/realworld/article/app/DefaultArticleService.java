package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.app.dto.ArticleSearchCondition;
import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleQueryRepository;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.exception.ArticleNotFoundException;
import io.realworld.common.exception.ArticleUserNotMatchedException;
import io.realworld.tag.app.TagService;
import io.realworld.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService {

    final private TagService tagService;
    final private ArticleRepository articleRepository;
    final private ArticleQueryRepository articleQueryRepository;

    @Override
    public Article createArticle(ArticleCreateDto dto, Long userId) {
        Article article = Article.builder()
                .title(dto.getArticleDto().getTitle())
                .description(dto.getArticleDto().getDescription())
                .body(dto.getArticleDto().getBody())
                .userId(userId)
                .build();

        if (dto.getArticleDto().getTags() != null) {
            Set<Tag> tags = tagService.createTags(dto.getArticleDto().getTags());
            article.addTags(tags);
        }

        return articleRepository.save(article);
    }

    @Override
    @Transactional(readOnly = true)
    public Article getArticle(String slug) {
        return articleRepository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Article> getArticles(String tag, String author, String favorited, Pageable pageable) {

        ArticleSearchCondition condition = ArticleSearchCondition.builder()
                .tag(tag)
                .author(author)
                .favorited(favorited)
                .build();

        return articleQueryRepository.search(condition, pageable);
    }

    @Override
    public Article updateArticle(ArticleUpdateDto dto, String slug, Long userId) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ArticleNotFoundException::new);

        if (article.getUserId().compareTo(userId) != 0) {
            throw new ArticleUserNotMatchedException();
        }

        if (dto.getArticleDto().getTags() != null) {
            Set<Tag> tags = tagService.createTags(dto.getArticleDto().getTags());
            article.updateTags(tags);
        }

        article.updateArticle(dto.getArticleDto().getTitle(), dto.getArticleDto().getBody(), dto.getArticleDto().getDescription());

        return article;
    }

    @Override
    public void deleteArticle(String slug, Long userId) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ArticleNotFoundException::new);

        if (article.getUserId().compareTo(userId) != 0) {
            throw new ArticleUserNotMatchedException();
        }

        articleRepository.delete(article);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Article> getArticlesByUserIds(List<Long> userIds, Pageable pageable) {
        return articleRepository.findByUserIdIn(userIds, pageable);
    }

}
