package io.realworld.comment.app;

import io.realworld.article.app.ArticleService;
import io.realworld.article.domain.Article;
import io.realworld.comment.domain.Comment;
import io.realworld.comment.domain.CommentValidator;
import io.realworld.comment.domain.repository.CommentRepository;
import io.realworld.common.exception.CommentNotFoundException;
import io.realworld.user.app.UserService;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {
    final private CommentRepository commentRepository;
    final private CommentValidator commentValidator;
    final private ArticleService articleService;
    final private UserService userService;

    @Override
    public Comment createComment(String body, String slug, Long userId) {
        Article article = articleService.getArticle(slug);
        User user = userService.getUserById(userId);

        Comment comment = Comment.builder()
                .userId(user.getId())
                .articleId(article.getId())
                .body(body)
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(String slug) {
        Article article = articleService.getArticle(slug);

        return commentRepository.findByArticleId(article.getId());
    }

    @Override
    public void deleteComment(Long commentId, String slug) {
        Article article = articleService.getArticle(slug);
        Comment comment = getComment(commentId);

        commentRepository.deleteByIdAndArticleId(comment.getId(), article.getId());
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
    }

}
