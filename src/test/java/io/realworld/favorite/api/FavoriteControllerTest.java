package io.realworld.favorite.api;

import io.realworld.article.domain.Article;
import io.realworld.article.domain.repository.ArticleRepository;
import io.realworld.common.WithDefaultUser;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteControllerTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithDefaultUser
    void favoriteArticle() throws Exception {
        // given
        String slug = "slug203";
        Article article = articleRepository.findBySlug(slug).orElseThrow();
        User user = userRepository.findById(article.getUserId()).orElseThrow();

        // when
        // then
        mockMvc.perform(post("/api/articles/{slug}/favorite", slug).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.article.title").value(article.getTitle()))
                .andExpect(jsonPath("$.article.slug").isNotEmpty())
                .andExpect(jsonPath("$.article.favorited").value(true))
                .andExpect(jsonPath("$.article.favoritesCount").value(2))
                .andExpect(jsonPath("$.article.author.following").value(false))
                .andExpect(jsonPath("$.article.author.username").value(user.getUsername()));
    }

    @Test
    @WithDefaultUser
    void unfavoriteArticle() throws Exception {
        // given
        String slug = "slug101";
        Article article = articleRepository.findBySlug(slug).orElseThrow();
        User user = userRepository.findById(article.getUserId()).orElseThrow();

        // when
        // then
        mockMvc.perform(delete("/api/articles/{slug}/favorite", slug).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.article.title").value(article.getTitle()))
                .andExpect(jsonPath("$.article.slug").isNotEmpty())
                .andExpect(jsonPath("$.article.favorited").value(false))
                .andExpect(jsonPath("$.article.favoritesCount").value(0))
                .andExpect(jsonPath("$.article.author.following").value(false))
                .andExpect(jsonPath("$.article.author.username").value(user.getUsername()));
    }
}

