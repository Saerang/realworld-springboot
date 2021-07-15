package io.realworld.user.api;

import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.app.ProfileService;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.http.MediaType.*;
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

    @Test
    void getProfile() throws Exception {
        //given
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username("realworld")
                .email("realworld@email.com")
                .password("1234")
                .build();
        userRepository.save(dto.toEntity());

        //when
        mockMvc.perform(get("/api/profiles/{username}", "realworld").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$..username").value("realworld"));

        //then
    }
}
