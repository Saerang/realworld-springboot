package io.realworld.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.realworld.common.WithDefaultUser;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @Test
    void createUser() throws Exception {
        // given
        String username = "new_realworld";
        String email = "new_realworld1@email.com";
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username(username)
                .email(email)
                .password("1234")
                .build();
        String jsonDto = objectMapper.writeValueAsString(dto);

        // when
        // then
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..token").isNotEmpty())
                .andExpect(jsonPath("$..username").value(username))
                .andExpect(jsonPath("$..email").value(email));
    }

    @Test
    void login() throws Exception {
        // given
        UserLoginRequestDto loginDto = UserLoginRequestDto.builder()
                .email("realworld1@email.com")
                .password("12345678")
                .build();

        String jsonDto = objectMapper.writeValueAsString(loginDto);

        // when
        // then
        mockMvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..token").isNotEmpty())
                .andExpect(jsonPath("$..username").value("realworld1"))
                .andExpect(jsonPath("$..email").value("realworld1@email.com"));
    }

    @Test
    @WithDefaultUser
    void getCurrentUser() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..token").isNotEmpty())
                .andExpect(jsonPath("$..username").value("realworld1"))
                .andExpect(jsonPath("$..email").value("realworld1@email.com"));
    }

    @Test
    @WithDefaultUser
    void updateUser() throws Exception {
        // given
        UserUpdateRequestDto dto = UserUpdateRequestDto.builder()
                .email("new_realworld1@email.com")
                .username("new_realworld")
                .password("87654321")
                .build();
        String jsonRequestDto = objectMapper.writeValueAsString(dto);

        // when
        // then
        mockMvc.perform(put("/api/user").contentType(MediaType.APPLICATION_JSON).content(jsonRequestDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..token").isNotEmpty())
                .andExpect(jsonPath("$..username").value("new_realworld"))
                .andExpect(jsonPath("$..email").value("new_realworld1@email.com"));
    }

}
