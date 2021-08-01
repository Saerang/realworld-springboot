package io.realworld.article.domain.repository;

import io.realworld.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByUserId(long userId);

    Optional<Article> findBySlug(String slug);

    Page<Article> findByIdIn(List<Long> ids, Pageable pageable);

    Page<Article> findByUserIdIn(List<Long> userIds, Pageable pageable);

}
