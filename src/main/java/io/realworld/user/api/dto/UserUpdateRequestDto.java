package io.realworld.user.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String email;
    private String username;
    private String password;
    private String image;
    private String bio;

    @Builder
    public UserUpdateRequestDto(String email, String username, String password, String image, String bio) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.image = image;
        this.bio = bio;
    }

}
