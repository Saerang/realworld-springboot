package io.realworld.user.domain;

import io.realworld.common.exception.PasswordNotMatchedException;
import io.realworld.user.api.SpringUserPasswordEncoder;
import io.realworld.user.api.UserPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    UserPasswordEncoder userPasswordEncoder;

    @BeforeEach
    void setUp() {
        this.userPasswordEncoder = new SpringUserPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Test
    void createUser() {
        //when
        User user = getUser();

        //then
        assertThat(user.getEmail()).isEqualTo("realworld@email.com");
        assertThat(user.getPassword()).isNotBlank();
        assertThat(user.getPassword()).isNotEqualTo("1234");
        assertThat(user.getUsername()).isEqualTo("realworld");
        assertThat(user.getBio()).isEqualTo("bio");
    }

    @Test
    void passwordMatches_equal() {
        //given
        User user = getUser();

        //when
        //then
        user.checkPassword("12345678", this.userPasswordEncoder);
    }

    @Test
    void passwordMatches_notEqual() {
        //given
        User user = getUser();

        //when
        //then
        assertThatThrownBy(() -> user.checkPassword("99999999", this.userPasswordEncoder)).
                isInstanceOf(PasswordNotMatchedException.class);
    }

    @Test
    void updateUserInfo() {
        //given
        User user = getUser();
        String email = "new_realworld@mail.com";
        String username = "new_realworld";
        String password = "987654321";
        String imag = "new_image";
        String bio = "new_bio";

        //when
        user.updateUserInfo(email, username, password, userPasswordEncoder, imag, bio);

        //then
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getUsername()).isEqualTo(username);
        user.checkPassword(password, userPasswordEncoder);
        assertThat(user.getImage()).isEqualTo(imag);
        assertThat(user.getBio()).isEqualTo(bio);
    }

    private User getUser() {
        return User.builder()
                .email("realworld@email.com")
                .username("realworld")
                .bio("bio")
                .password("12345678")
                .userPasswordEncoder(this.userPasswordEncoder)
                .build();
    }
}
