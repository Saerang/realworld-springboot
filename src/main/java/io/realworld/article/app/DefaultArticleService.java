package io.realworld.article.app;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.ArticleUpdateDto;
import io.realworld.article.domain.Article;
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

    @Override
    public Article createArticle(ArticleCreateDto dto, Long userId) {
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
    @Transactional(readOnly = true)
    public Article getArticle(String slug) {
        return articleRepository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
    }

    // TODO: queryDsl 로 바꿔서 동적 쿼리 작성해야됨.
    @Override
    @Transactional(readOnly = true)
    public Page<Article> getArticles(String tag, String author, String favorited, Pageable pageable) {
        return articleRepository.findAllWithTag(pageable);
    }

    @Override
    public Article updateArticle(ArticleUpdateDto dto, String slug, Long userId) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ArticleNotFoundException::new);

        if (article.getUserId().compareTo(userId) != 0) {
            throw new ArticleUserNotMatchedException();
        }

        if (dto.getTags() != null) {
            Set<Tag> tags = tagService.createTags(dto.getTags());
            article.updateTags(tags);
        }

        article.updateArticle(dto.getTitle(), dto.getBody(), dto.getDescription());

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
    public Page<Article> getArticlesByArticleIds(List<Long> articleIds, Pageable pageable) {
        return articleRepository.findByIdIn(articleIds, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Article> getArticlesByUserIds(List<Long> userIds, Pageable pageable) {
        return articleRepository.findAllByUserId(userIds, pageable);
    }

}
