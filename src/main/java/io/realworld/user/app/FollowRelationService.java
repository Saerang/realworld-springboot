package io.realworld.user.app;

import io.realworld.user.domain.FollowRelation;

import java.util.List;

public interface FollowRelationService {
    boolean isFollowing(Long followeeId, Long followerId);

    List<FollowRelation> getFollowRelations(Long followerId);
}
