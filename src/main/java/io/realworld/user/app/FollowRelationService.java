package io.realworld.user.app;

import io.realworld.user.domain.FollowRelation;

import java.util.List;

public interface FollowRelationService {
    boolean isFollowing(long followeeId, long followerId);

    List<FollowRelation> getFollowRelations(long followerId);
}
