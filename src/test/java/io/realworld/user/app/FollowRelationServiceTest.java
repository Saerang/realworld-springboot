package io.realworld.user.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FollowRelationServiceTest {

    @Autowired
    FollowRelationService followRelationService;

    @Test
    void getFolloweeUser() {
        // given
        // when
        boolean isFollowing = followRelationService.isFollowing(101L, 102L);

        // then
        assertThat(isFollowing).isTrue();
    }

}
