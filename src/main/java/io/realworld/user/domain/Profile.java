package io.realworld.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {
    private String username;
    private String bio;
    private String image;

    @Builder
    public Profile(String username, String bio, String image) {
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public void updateProfile(String username, String bio, String image) {
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

}
