package io.realworld.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.realworld.comment.api.dto.CommentCreateDto;
import io.realworld.common.WithDefaultUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static io.realworld.Fixtures.defaultUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithDefaultUser
    void createComment() throws Exception {
        // given
        CommentCreateDto.CommentDto commentDto = CommentCreateDto.CommentDto.builder().body("body").build();
        CommentCreateDto commentCreateDto = CommentCreateDto.builder().commentDto(commentDto).build();

        String jsonDto = objectMapper.writeValueAsString(commentCreateDto);

        // when
        // then
        mockMvc.perform(post("/api/articles/{slug}/comments", "slug101").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment.id").isNumber())
                .andExpect(jsonPath("$.comment.createdAt").exists())
                .andExpect(jsonPath("$.comment.updatedAt").exists())
                .andExpect(jsonPath("$.comment.body").value(commentDto.getBody()))
                .andExpect(jsonPath("$.comment.author.username").value(defaultUser().getUsername()))
                .andExpect(jsonPath("$.comment.author.bio").value(defaultUser().getBio()))
                .andExpect(jsonPath("$.comment.author.image").value(defaultUser().getImage()))
                .andExpect(jsonPath("$.comment.author.following").value(false));
    }

    @Test
    @WithDefaultUser
    void getComments() throws Exception {
        mockMvc.perform(get("/api/articles/{slug}/comments", "slug101").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments.length()").value(2))
                .andExpect(jsonPath("$.comments[0].id").isNumber())
                .andExpect(jsonPath("$.comments[1].id").isNumber())
                .andExpect(jsonPath("$.comments[0].createdAt").exists())
                .andExpect(jsonPath("$.comments[0].updatedAt").exists())
                .andExpect(jsonPath("$.comments[0].body").exists())
                .andExpect(jsonPath("$.comments[0].author.username").value(defaultUser().getUsername()))
                .andExpect(jsonPath("$.comments[0].author.bio").value(defaultUser().getBio()))
                .andExpect(jsonPath("$.comments[0].author.image").value(defaultUser().getImage()))
                .andExpect(jsonPath("$.comments[0].author.following").value(false))
                .andExpect(jsonPath("$.comments[1].author.following").value(true));
    }
}
