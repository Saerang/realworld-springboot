package io.realworld.common.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    void createToken() {
        // given
        // when
        String token = jwtTokenProvider.createToken(1);

        // then
        assertThat(token).isNotNull();
    }

    @Test
    void validToken() {
        // given
        String token = jwtTokenProvider.createToken(1);

        // when
        boolean validateToken = jwtTokenProvider.validateToken(token);

        // then
        assertThat(validateToken).isTrue();
    }

}
