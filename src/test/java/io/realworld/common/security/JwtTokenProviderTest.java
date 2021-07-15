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

    @BeforeEach
    void setUp() {
//        tokenProvider = new TokenProvider("c2FlcmFuZy1yZWFsd29ybGQtand0LXNlY3JldC1rZXkuIEkgd2FudCB0byB3b3JrIGFzIGEgYmFlbWluLiBJIHdhbnQgdG8gZGV2ZWxvcCB3ZWxsLg==", 86400);
    }

    @Test
    void createToken() {
        // given
        String token = jwtTokenProvider.createToken(1);

        // when

        // then
        assertThat(token).isNotNull();
    }

    @Test
    void validToken() {
        // given
        String token= "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjI2MzU2NjQ2fQ.pjNDOLI7GPhYT4lrnKqmrQRERoFdGrGbckZkiKXSQkYy-uZnLAp1e7g8m_FkOFk6wanvQvqBs_Ug6gBeYU4Wmg";

        // when
        jwtTokenProvider.validateToken(token);

        // then
    }

}
