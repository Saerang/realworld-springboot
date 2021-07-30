package io.realworld.user.app;

import io.realworld.user.domain.User;
import org.apache.commons.lang3.tuple.Pair;

public interface ProfileService {
    Pair<User, Boolean> getProfile(String profileUserName, Long followeeUserId);

    Pair<User, Boolean> getProfile(Long profileUserId, Long followeeUserId);

    Pair<User, Boolean> followUser(String followeeUserName, Long followerUserId);

    Pair<User, Boolean> unfollowUser(String followeeUserName, Long followerUserId);
}
