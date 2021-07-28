package io.realworld.comment.domain;

import org.junit.jupiter.api.Test;

import static io.realworld.Fixtures.aComment;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {

    @Test
    void createComment() {
        //given
        String body = "body";

        //when
        Comment comment = aComment().body("body").build();

        //then
        assertThat(comment.getBody()).isEqualTo(body);
    }
}
