package io.realworld.article.domain.repository;

import io.realworld.article.domain.Article;
import io.realworld.favorite.app.enumerate.FavoriteType;
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

    @Query(value = "select distinct a from Article a " +
            "left join fetch a.articleTags at " +
            "left join fetch at.tag t ",
    countQuery = "select count(distinct a) from Article a left join a.articleTags at left join at.tag t")
    Page<Article> findAllWithTag(Pageable pageable);

    Page<Article> findByIdIn(List<Long> ids, Pageable pageable);

    @Query(value = "select a from Article a " +
            "left join fetch a.articleTags at " +
            "left join fetch at.tag t " +
            "where a.userId in (:userIds)",
            countQuery = "select count(a) from Article a join a.articleTags at join at.tag t where a.userId in (:userIds)")
    Page<Article> findAllByUserId(@Param("userIds") List<Long> userIds, Pageable pageable);
}
