package io.realworld.article.domain.repository;

import io.realworld.article.domain.Tag;
import io.realworld.user.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

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
        Tag findTag = tagRepository.findById(saveTag.getId()).orElse(new Tag(null, null));

        // then
        assertThat(tag.getTag()).isEqualTo(findTag.getTag());
    }
}
