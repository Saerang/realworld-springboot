package io.realworld.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void createUser() {
        //given
        //when
        User user = User.builder()
                .email("realworld@email.com")
                .username("realworld")
                .bio("bio")
                .password("1234")
                .build();

        //then
        assertThat(user.getEmail()).isEqualTo("realworld@email.com");
        assertThat(user.getPassword()).isEqualTo("1234");
        assertThat(user.getUsername()).isEqualTo("realworld");
        assertThat(user.getBio()).isEqualTo("bio");
    }
}
