package io.realworld.article.app;

import io.realworld.article.domain.ArticleTag;
import io.realworld.article.domain.repository.ArticleTagRepository;
import io.realworld.tag.domain.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DefaultArticleTagService implements ArticleTagService{

    final private ArticleTagRepository articleTagRepository;
    final private TagRepository tagRepository;

    public DefaultArticleTagService(ArticleTagRepository articleTagRepository, TagRepository tagRepository) {
        this.articleTagRepository = articleTagRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<ArticleTag> getArticleTags(List<Long> articleIds) {
        return articleTagRepository.findAllWithTagByArticle_IdIn(articleIds);
    }
}
