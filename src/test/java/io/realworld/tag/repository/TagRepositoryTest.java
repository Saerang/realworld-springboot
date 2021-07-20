package io.realworld.tag.repository;

import io.realworld.common.exception.TagNotFoundException;
import io.realworld.tag.domain.Tag;
import io.realworld.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void persistence() {
        // given
        Tag tag = Tag.builder().tag("tag").build();

        // when
        Tag saveTag = tagRepository.save(tag);

        em.flush();
        em.clear();

        Tag findTag = tagRepository.findById(saveTag.getId()).orElseThrow(() -> new TagNotFoundException("tag"));

        // then
        assertThat(tag.getTag()).isEqualTo(findTag.getTag());
    }
}
