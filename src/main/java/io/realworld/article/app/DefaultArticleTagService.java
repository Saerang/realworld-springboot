package io.realworld.article.app;

import io.realworld.article.domain.ArticleTag;
import io.realworld.article.domain.repository.ArticleTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DefaultArticleTagService implements ArticleTagService{

    final private ArticleTagRepository articleTagRepository;

    public DefaultArticleTagService(ArticleTagRepository articleTagRepository) {
        this.articleTagRepository = articleTagRepository;
    }

    @Override
    public List<ArticleTag> getArticleTags(List<Long> articleIds) {
        return articleTagRepository.findAllWithTagByArticle_IdIn(articleIds);
    }
}
