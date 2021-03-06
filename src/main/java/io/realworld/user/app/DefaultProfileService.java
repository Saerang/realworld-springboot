package io.realworld.user.app;

import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProfileService implements ProfileService {

    private final FollowRelationRepository followRelationRepository;
    private final UserService userService;

    @Override
    public Pair<User, Boolean> getProfile(String profileUserName, Long followeeUserId) {
        User user = userService.getUserByUsername(profileUserName);

        boolean isFollowing = false;
        if (followeeUserId != null) {
            Optional<FollowRelation> followRelation = followRelationRepository.findById(new FollowRelationId(user.getId(), followeeUserId));
            if(followRelation.isPresent()) {
                isFollowing = true;
            }
        }

        return Pair.of(user, isFollowing);
    }

    @Override
    public Pair<User, Boolean> getProfile(Long profileUserId, Long followeeUserId) {
        User followeeUser = userService.getUserById(profileUserId);

        boolean isFollowing = false;
        if (followeeUserId != null) {
            Optional<FollowRelation> followRelation = followRelationRepository.findById(new FollowRelationId(profileUserId, followeeUserId));
            if(followRelation.isPresent()) {
                isFollowing = true;
            }
        }

        return Pair.of(followeeUser, isFollowing);
    }

    @Override
    public Pair<User, Boolean> followUser(String profileUsername, Long followerUserId) {
        User followeeUser = userService.getUserByUsername(profileUsername);

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.save(followRelation);

        return Pair.of(followeeUser, true);
    }

    @Override
    public Pair<User, Boolean> unfollowUser(String profileUsername, Long followerUserId) {
        User followeeUser = userService.getUserByUsername(profileUsername);

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.delete(followRelation);

        return Pair.of(followeeUser, false);
    }
}
