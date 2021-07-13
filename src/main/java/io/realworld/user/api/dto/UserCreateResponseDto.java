package io.realworld.user.api.dto;

import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateResponseDto {
    private String username;
    private String email;
    private String password;

    @Builder
    public UserCreateResponseDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User toEntity() {
        Profile profile = Profile.builder()
                .username(this.username)
                .build();

        return User.builder()
                .profile(profile)
                .password(this.password)
                .email(this.email)
                .build();
    }
}
