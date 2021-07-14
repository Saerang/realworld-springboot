package io.realworld.user.domain.service;

import io.realworld.RealwroldSpringbootTddApplication;
import io.realworld.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtServiceImplTest {

    @Autowired
    JwtService jwtService;

    @Test
    void createToken() {
        //given
        String token = jwtService.createToken(new User("realworld@email.com", "1234", "realwrold"));

        //when

        //then
        assertThat(token).isEqualTo("token");
    }

}