package io.realworld.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    void getCurrentUser() throws Exception {
        //given
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username("realworld")
                .email("realworld@gmail.com")
                .password("1234")
                .build();
        String jsonDto = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..username").value("realworld"))
                .andExpect(jsonPath("$..email").value("realworld@gmail.com"))
                .andExpect(jsonPath("$..password").value("1234"));
    }

}
