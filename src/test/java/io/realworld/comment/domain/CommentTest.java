package io.realworld.comment.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {

    @Test
    void createComment() {
        //given
        String body = "body";

        //when
        Comment comment = new Comment(body);

        //then
        assertThat(comment.getBody()).isEqualTo(body);
    }
}
