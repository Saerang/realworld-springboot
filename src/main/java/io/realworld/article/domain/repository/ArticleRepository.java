package io.realworld.article.domain.repository;

import io.realworld.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByUserId(long userId);

    Optional<Article> findBySlug(String slug);

    @Query("select a from Article a " +
            "join a.articleTags at " +
            "join at.tag t " +
            "where t.tag = :tag")
    List<Article> findByTag(@Param("tag") String tag);
}
