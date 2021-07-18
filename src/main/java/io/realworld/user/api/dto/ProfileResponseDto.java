package io.realworld.user.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {
    private ProfileDto profile;

    @Getter
    public static class ProfileDto {
        private String username;
        private String bio;
        private String image;
        private boolean following;

        @Builder
        public ProfileDto(String username, String bio, String image, boolean following) {
            this.username = username;
            this.bio = bio;
            this.image = image;
            this.following = following;
        }
    }

    @Builder
    public ProfileResponseDto(ProfileDto profile) {
        this.profile = profile;
    }
}
