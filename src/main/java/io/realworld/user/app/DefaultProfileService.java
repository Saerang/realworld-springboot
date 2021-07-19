package io.realworld.user.app;

import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultProfileService implements ProfileService {

    private final FollowRelationRepository followRelationRepository;
    private final UserService userService;

    @Override
    public User getProfile(String username) {
        User user = userService.getUserByUsername(username);
        user.userUnfollow();
        return user;
    }

    @Override
    public User followUser(long followerUserId, String username) {
        // TODO: controller 에서 id를 넘길까, userService 혹은 현제 세션에 있는 유저로 가져올까.
        // User user = userService.getCurrentUser();
        User followeeUser = userService.getUserByUsername(username);
        followeeUser.userFollow();

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.save(followRelation);

        return followeeUser;
    }

    @Override
    public User unfollowUser(long followerUserId, String username) {
        // TODO: controller 에서 id를 넘길까, userService 혹은 현제 세션에 있는 유저로 가져올까.
        // User user = userService.getCurrentUser();
        User followeeUser = userService.getUserByUsername(username);
        followeeUser.userUnfollow();

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.delete(followRelation);

        return followeeUser;
    }
}
