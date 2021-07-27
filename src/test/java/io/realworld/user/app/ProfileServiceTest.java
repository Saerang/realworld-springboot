package io.realworld.user.app;

import io.realworld.user.api.UserPasswordEncoder;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import io.realworld.user.domain.repository.UserRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProfileServiceTest {

    @Autowired
    ProfileService profileService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPasswordEncoder userPasswordEncoder;
    @Autowired
    FollowRelationRepository followRelationRepository;
    @Autowired
    EntityManager em;

    @Test 
    void getProfile() {
        //given
        User user = User.createWithUserPasswordEncoder("user@email.com", "1234", userPasswordEncoder, "usrname");
        userRepository.save(user);

        //when
        Pair<User, Boolean> result = profileService.getProfile(user.getUsername());

        //then
        assertThat(result.getLeft().getUsername()).isEqualTo(user.getUsername());
    }
    
    @Test
    void userFollow() {
        //given
        User follower = User.createWithUserPasswordEncoder("followerd@email.com", "1234", userPasswordEncoder, "follower");
        User followee = User.createWithUserPasswordEncoder("followee@email.com", "1234", userPasswordEncoder, "followee");
        userRepository.saveAll(Arrays.asList(follower, followee));

        // em.flush();
        // em.clear();

        //when
        Pair<User, Boolean> result = profileService.followUser(follower.getId(), followee.getUsername());

        //then
        assertThat(result.getRight()).isTrue();
        assertThat(result.getLeft().getUsername()).isEqualTo(followee.getUsername());
    }

    @Test
    void userUnfollow() {
        //given
        User follower = User.createWithUserPasswordEncoder("followerd@email.com", "1234", userPasswordEncoder, "follower");
        User followee = User.createWithUserPasswordEncoder("followee@email.com", "1234", userPasswordEncoder, "followee");
        userRepository.saveAll(Arrays.asList(follower, followee));
        FollowRelation followRelation = new FollowRelation(follower.getId(), followee.getId());
        followRelationRepository.save(followRelation);

        em.flush();
        em.clear();

        //when
        Pair<User, Boolean> result = profileService.unfollowUser(follower.getId(), followee.getUsername());
        List<FollowRelation> followRelations = followRelationRepository.findByFolloweeId(follower.getId());

        //then
        assertThat(result.getRight()).isFalse();
        assertThat(result.getLeft().getUsername()).isEqualTo(followee.getUsername());
        assertThat(followRelations).hasSize(0);
    }
}
