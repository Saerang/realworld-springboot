package io.realworld.user.app;

import io.realworld.user.domain.User;

public interface ProfileService {
    User getProfile(String username);

    User followUser(long followerUserId, String username);

    User unfollowUser(long followerUserId, String username);
}
