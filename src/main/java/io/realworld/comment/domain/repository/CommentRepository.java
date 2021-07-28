package io.realworld.comment.domain.repository;

import io.realworld.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);

    void deleteByIdAndArticleId(Long id, Long articleId);
}
