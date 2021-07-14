package io.realworld.user.app;

import io.realworld.user.api.dto.ProfileResponseDto;

public interface ProfileService {
    ProfileResponseDto getProfile(String username);

    ProfileResponseDto followUser(long followerUserId, String username);

    ProfileResponseDto unfollowUser(long followerUserId, String username);
}
