package io.realworld.comment.domain.repository;

import io.realworld.comment.domain.Comment;
import io.realworld.common.exception.CommentNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TestEntityManager em;

    @Test
    void persistence() {
        //given
        Comment comment = Comment.builder()
                .build();

        assertThat(comment.getId()).isNull();

        //when
        commentRepository.save(comment);
        assertThat(comment.getId()).isGreaterThan(0);

        em.flush();
        em.clear();

        Comment findComment = commentRepository.findById(comment.getId()).orElseThrow(() -> new CommentNotFoundException(comment.getId()));

        //then
        assertThat(comment.getId()).isEqualTo(findComment.getId());
        assertThat(comment.getBody()).isEqualTo(findComment.getBody());
    }
}
