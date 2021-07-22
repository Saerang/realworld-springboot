package io.realworld.user.domain.repository;

import io.realworld.user.domain.FollowRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FollowRelationRepositoryTest {

    @Autowired
    FollowRelationRepository followRelationRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void persistence() {
        //given
        FollowRelation followRelation = new FollowRelation(1L, 2L);

        //when
        followRelationRepository.save(followRelation);
        em.flush();
        em.clear();

        //then
        List<FollowRelation> findFollowerRelation = followRelationRepository.findByFolloweeId(2L);
        assertThat(findFollowerRelation.get(0)).isEqualTo(followRelation);
    }
}
