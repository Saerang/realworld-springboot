package io.realworld.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {
    @Column(unique = true)
    private String username;

    private String bio;

    private String image;

    @Transient
    private boolean following;

    @Builder
    public Profile(String username, String bio, String image) {
        Assert.state(StringUtils.isNotBlank(username), "username may not be blank.");

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
