package io.realworld.user.api;

import io.realworld.common.security.JwtTokenProvider;
import io.realworld.user.app.ProfileService;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
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
        mockMvc.perform(get("/api/profiles/{username}", "realworld1").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$..username").value("realworld1"));
    }

    @Test
    void followUser() throws Exception {
        // given
        String token = jwtTokenProvider.createToken(1);

        // when 
        // then
        mockMvc.perform(post("/api/profiles/{username}/follow", "realworld2").contentType(APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Token " + token))
                .andDo(print())
                .andExpect(jsonPath("$..username").value("realworld2"))
                .andExpect(jsonPath("$..following").value(true));
    }

    @Test
    void unfollowUser() throws Exception {
        // given
        String token = jwtTokenProvider.createToken(1);

        // when
        // then
        mockMvc.perform(delete("/api/profiles/{username}/follow", "realworld2").contentType(APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Token " + token))
                .andDo(print())
                .andExpect(jsonPath("$..username").value("realworld2"))
                .andExpect(jsonPath("$..following").value(false));
    }
}
