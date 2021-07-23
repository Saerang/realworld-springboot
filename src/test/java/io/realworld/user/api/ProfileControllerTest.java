package io.realworld.user.api;

import io.realworld.common.WithDefaultUser;
import io.realworld.common.security.JwtTokenProvider;
import io.realworld.user.app.ProfileService;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    ProfileService profileService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    void getProfile() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/api/profiles/{username}", "realworld101").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$..username").value("realworld101"));
    }

    @Test
    @WithDefaultUser
    void followUser() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/api/profiles/{username}/follow", "realworld102").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$..username").value("realworld102"))
                .andExpect(jsonPath("$..following").value(true));
    }

    @Test
    @WithDefaultUser
    void unfollowUser() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(delete("/api/profiles/{username}/follow", "realworld102").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$..username").value("realworld102"))
                .andExpect(jsonPath("$..following").value(false));
    }
}
