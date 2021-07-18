package io.realworld.user.app;

import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import io.realworld.user.domain.repository.UserRepository;
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
    FollowRelationRepository followRelationRepository;
    @Autowired
    EntityManager em;

    @Test 
    void getProfile() {
        //given
        User user = new User("user@email.com", "1234", "usrname");
        userRepository.save(user);
         
        //when
        ProfileResponseDto dto = profileService.getProfile(user.getProfile().getUsername());
         
        //then
        assertThat(dto.getProfile().getUsername()).isEqualTo(user.getProfile().getUsername());
    }
    
    @Test
    void userFollow() {
        //given
        User follower = new User("followerd@email.com", "1234", "follower");
        User followee = new User("followee@email.com", "1234", "followee");
        userRepository.saveAll(Arrays.asList(follower, followee));

        // em.flush();
        // em.clear();

        //when
        ProfileResponseDto profileResponseDto = profileService.followUser(follower.getId(), followee.getProfile().getUsername());

        //then
        assertThat(profileResponseDto.getProfile().isFollowing()).isTrue();
        assertThat(profileResponseDto.getProfile().getUsername()).isEqualTo(followee.getProfile().getUsername());
    }

    @Test
    void userUnfollow() {
        //given
        User follower = new User("followerd@email.com", "1234", "follower");
        User followee = new User("followee@email.com", "1234", "followee");
        userRepository.saveAll(Arrays.asList(follower, followee));
        FollowRelation followRelation = new FollowRelation(follower.getId(), followee.getId());
        followRelationRepository.save(followRelation);

        em.flush();
        em.clear();

        //when
        ProfileResponseDto profileResponseDto = profileService.unfollowUser(follower.getId(), followee.getProfile().getUsername());
        List<FollowRelation> followRelations = followRelationRepository.findByFollowRelationId_FollowerId(follower.getId());

        //then
        assertThat(profileResponseDto.getProfile().isFollowing()).isFalse();
        assertThat(profileResponseDto.getProfile().getUsername()).isEqualTo(followee.getProfile().getUsername());
        assertThat(followRelations).hasSize(0);
    }
}
