package io.realworld.article.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.realworld.common.WithDefaultUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithDefaultUser
    void getArticles() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/articles").contentType(MediaType.APPLICATION_JSON)
                .param("offset", "0" )
                .param("limit", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articles.length()").value(5))
                .andExpect(jsonPath("$.articles[0].title").value("title203"))
                .andExpect(jsonPath("$.articles[1].title").value("title202"))
                .andExpect(jsonPath("$.articles[2].title").value("title201"));
    }

    @Test
    @WithDefaultUser
    void getFeedArticles() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/articles/feed").contentType(MediaType.APPLICATION_JSON)
                .param("offset", "0" )
                .param("limit", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articles.length()").value(3))
                .andExpect(jsonPath("$.articles[0].author.following").value(true))
                .andExpect(jsonPath("$.articles[1].author.following").value(true))
                .andExpect(jsonPath("$.articles[2].author.following").value(true))
                .andExpect(jsonPath("$.articles[0].title").value("title106"))
                .andExpect(jsonPath("$.articles[1].title").value("title105"))
                .andExpect(jsonPath("$.articles[2].title").value("title104"));
    }

    @Test
    @WithDefaultUser
    void getArticle_login() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/articles/slug104").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.article.title").value("title104"))
                .andExpect(jsonPath("$.article.slug").value("slug104"))
                .andExpect(jsonPath("$.article.favorited").value(true))
                .andExpect(jsonPath("$.article.favoritesCount").value(2))
                .andExpect(jsonPath("$.article.author.following").value(true))
                .andExpect(jsonPath("$.article.author.username").value("realworld102"));
    }

    @Test
    void getArticle_not_login() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/articles/slug104").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.article.title").value("title104"))
                .andExpect(jsonPath("$.article.slug").value("slug104"))
                .andExpect(jsonPath("$.article.favorited").value(false))
                .andExpect(jsonPath("$.article.favoritesCount").value(2))
                .andExpect(jsonPath("$.article.author.following").value(false))
                .andExpect(jsonPath("$.article.author.username").value("realworld102"));
    }
}
