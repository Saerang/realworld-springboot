package io.realworld.user.api.dto;

import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String username;
    private String email;
    private String password;

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
