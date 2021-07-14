package io.realworld.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTest {

    @Test
    void createProfile() {
        //given
        Profile profile = new Profile("realworld", "bio", "image");

        //when

        //then
        assertThat(profile.getUsername()).isEqualTo("realworld");
    }
}
