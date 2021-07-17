package io.realworld.user.domain;

import io.realworld.article.domain.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TagTest {

    @Test
    void createTag() {
        // given
        Tag tag = Tag.builder().tag("tag").build();

        // when

        // then
        assertThat(tag.getTag()).isEqualTo("tag");
    }
}
