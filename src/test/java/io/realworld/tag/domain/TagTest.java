package io.realworld.tag.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TagTest {

    @Test
    void createTag() {
        // given
        // when
        Tag tag = Tag.builder().tag("tag").build();

        // then
        assertThat(tag.getTag()).isEqualTo("tag");
    }
}
