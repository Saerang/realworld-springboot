package io.realworld.user.api;

import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "jake", roles = "USER")
    void getCurrentUser() {
        //given

        //when

        //then
    }

    private User getDefaultUser() {
        Profile profile = Profile.builder()
                .username("realworld")
                .bio("I work at statefarm")
                .build();
        return User.builder()
                .profile(profile)
                .email("realworld@gmail.com")
                .build();
    }
}