package io.realworld.user.app;

import io.realworld.user.domain.User;
import org.apache.commons.lang3.tuple.Pair;

public interface ProfileService {
    Pair<User, Boolean> getProfile(String username);

    Pair<User, Boolean> followUser(Long followerUserId, String username);

    Pair<User, Boolean> unfollowUser(Long followerUserId, String username);
}
