package io.realworld.user.app;

public interface FollowRelationService {
    boolean isFollowing(long followeeId, long followerId);
}
