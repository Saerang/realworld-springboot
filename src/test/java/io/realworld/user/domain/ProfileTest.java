package io.realworld.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTest {

    @Test
    void createProfile() {
        //given
        //when
        Profile profile = new Profile("realworld", "bio", "image");

        //then
        assertThat(profile.getUsername()).isEqualTo("realworld");
    }
}
