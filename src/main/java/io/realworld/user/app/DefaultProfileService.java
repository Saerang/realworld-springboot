package io.realworld.user.app;

import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultProfileService implements ProfileService {

    private final FollowRelationRepository followRelationRepository;
    private final UserService userService;

    @Override
    public Pair<User, Boolean> getProfile(String username) {
        User user = userService.getUserByUsername(username);
        //user.userUnfollow();
        return Pair.of(user, true);
    }

    @Override
    public Pair<User, Boolean> followUser(long followerUserId, String username) {
        // TODO: controller 에서 id를 넘길까, userService 혹은 현제 세션에 있는 유저로 가져올까.
        // User user = userService.getCurrentUser();
        User followeeUser = userService.getUserByUsername(username);

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.save(followRelation);

        return Pair.of(followeeUser, true);
    }

    @Override
    public Pair<User, Boolean> unfollowUser(long followerUserId, String username) {
        // TODO: controller 에서 id를 넘길까, userService 혹은 현제 세션에 있는 유저로 가져올까.
        // User user = userService.getCurrentUser();
        User followeeUser = userService.getUserByUsername(username);

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.delete(followRelation);

        return Pair.of(followeeUser, false);
    }
}
