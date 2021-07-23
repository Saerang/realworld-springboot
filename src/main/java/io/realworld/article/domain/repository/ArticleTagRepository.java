package io.realworld.article.domain.repository;

import io.realworld.article.domain.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {

    @Query("select at from ArticleTag at join fetch at.tag t " +
            "where at.article.id in (:articleIds)")
    List<ArticleTag> findAllWithTagByArticle_IdIn(@Param("articleIds") List<Long> articleIds);

}
