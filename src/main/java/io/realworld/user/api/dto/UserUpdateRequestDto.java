package io.realworld.user.api.dto;

import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import lombok.Builder;

public class UserUpdateRequestDto {
    private final String email;
    private final String bio;
    private final String image;

    @Builder
    public UserUpdateRequestDto(String email, String bio, String image) {
        this.email = email;
        this.bio = bio;
        this.image = image;
    }

    public User toEntity() {
        Profile profile = Profile.builder()
                .bio(this.bio)
                .image(this.image)
                .build();

        return User.builder()
                .profile(profile)
                .email(this.email)
                .build();
    }
}
