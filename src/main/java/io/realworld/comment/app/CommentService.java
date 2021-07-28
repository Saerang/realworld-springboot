package io.realworld.comment.app;

import io.realworld.comment.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(String body, String slug, Long userId);

    List<Comment> getComments(String slug);

    void deleteComment(Long commentId, String slug);
}
