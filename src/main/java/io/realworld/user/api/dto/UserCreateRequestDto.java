package io.realworld.user.api.dto;

import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequestDto {
    private final String username;
    private final String email;
    private final String password;

    @Builder
    public UserCreateRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        Profile profile = Profile.builder()
                .username(this.username)
                .build();

        return User.builder()
                .profile(profile)
                .email(this.email)
                .password(this.password)
                .build();
    }

}
