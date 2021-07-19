package io.realworld.user.app;

import io.realworld.common.WithDefaultUser;
import io.realworld.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AuthenticationServiceTest {

    @Autowired
    AuthenticationService authenticationService;

    @Test
    @WithDefaultUser
    void getCurrentUser() {
        //given

        //when
        User user = authenticationService.getCurrentUser();

        //then
        assertThat(user.getEmail()).isEqualTo("realworld1@email.com");
    }
}
