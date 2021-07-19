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
        return Pair.of(user, true);
    }

    @Override
    public Pair<User, Boolean> followUser(long followerUserId, String username) {
        User followeeUser = userService.getUserByUsername(username);

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.save(followRelation);

        return Pair.of(followeeUser, true);
    }

    @Override
    public Pair<User, Boolean> unfollowUser(long followerUserId, String username) {
        User followeeUser = userService.getUserByUsername(username);

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.delete(followRelation);

        return Pair.of(followeeUser, false);
    }
}
