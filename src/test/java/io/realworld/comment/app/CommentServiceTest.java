package io.realworld.comment.app;

import io.realworld.comment.domain.Comment;
import io.realworld.comment.domain.repository.CommentRepository;
import io.realworld.common.exception.ArticleNotFoundException;
import io.realworld.common.exception.CommentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static io.realworld.Fixtures.aComment;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;

    @Test
    void createComment() {
        // given
        String slug = "slug101";
        String body = "body";
        long userId = 101L;

        // when
        Comment comment = commentService.createComment(body, slug, userId);

        Comment findComment = commentRepository.findById(comment.getId()).orElseThrow();

        // then
        assertThat(findComment.getBody()).isEqualTo(body);
        assertThat(findComment.getUserId()).isEqualTo(userId);
    }

    @Test
    void getComments() {
        // given
        String slug = "slug101";

        // when
        List<Comment> comments = commentService.getComments(slug);

        // then
        assertThat(comments).extracting("articleId").containsOnly(101L);
        assertThat(comments).extracting("userId").containsExactlyInAnyOrder(101L, 102L);
        assertThat(comments).extracting("body").containsExactlyInAnyOrder("body101", "body102");
    }
    
    @Test
    void deleteComment() {
        // given 
        String slug = "slug101";
        Long commentId = 101L;

        Optional<Comment> beforeComment = commentRepository.findById(commentId);
        assertThat(beforeComment).isNotEmpty();

        // when 
        commentService.deleteComment(commentId, slug);
        Optional<Comment> afterComment = commentRepository.findById(commentId);

        // then
        assertThat(afterComment).isEmpty();
    }

    @Test
    void deleteComment_notFoundArticle() {
        // given
        String slug = "slug999";
        Long commentId = 101L;

        // when
        // then
        assertThatThrownBy(() -> commentService.deleteComment(commentId, slug)).isInstanceOf(ArticleNotFoundException.class);
    }

    @Test
    void deleteComment_notFoundComment() {
        // given
        String slug = "slug101";
        Long commentId = 999L;

        // when
        // then
        assertThatThrownBy(() -> commentService.deleteComment(commentId, slug)).isInstanceOf(NoSuchElementException.class);
    }
}
