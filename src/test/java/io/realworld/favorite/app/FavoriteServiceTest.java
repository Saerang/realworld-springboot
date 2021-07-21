package io.realworld.favorite.app;

import io.realworld.common.exception.FavoriteNotFoundException;
import io.realworld.favorite.domain.Favorite;
import io.realworld.favorite.domain.FavoriteId;
import io.realworld.favorite.domain.repository.FavoriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static io.realworld.favorite.app.enumerate.FavoriteType.ARTICLE;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FavoriteServiceTest {

    @Autowired
    FavoriteServiceFactory favoriteServiceFactory;
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    EntityManager em;

    @Test
    void getFavorited_false() {
        // given
        // when
        boolean favorited = favoriteServiceFactory.getService(ARTICLE).isFavorited(1, 2);

        // then
        assertThat(favorited).isFalse();
    }

    @Test
    void getFavorited_true() {
        // given
        Favorite favorite = Favorite.builder().userId(1).favoritedId(2).favoriteType(ARTICLE).build();
        favoriteRepository.save(favorite);

        // when
        boolean favorited = favoriteServiceFactory.getService(ARTICLE).isFavorited(1, 2);

        // then
        assertThat(favorited).isTrue();
    }
    
    @Test
    void getFavorites() {
        // given 
        
        // when 
         
        // then 
    }

    @Test
    void favoriteAuthor() {
        // given
        FavoriteId favoriteId = FavoriteId.builder().userId(1L).favoritedId(2L).favoriteType(ARTICLE).build();

        // when
        favoriteServiceFactory.getService(ARTICLE).favoriteAuthor(1, 2);
        em.flush();
        em.clear();

        Favorite favorite = favoriteRepository.findById(favoriteId).orElseThrow(FavoriteNotFoundException::new);

        // then
        assertThat(favorite.getFavoriteId().getUserId()).isEqualTo(1);
        assertThat(favorite.getFavoriteId().getFavoritedId()).isEqualTo(2);
    }

    @Test
    void unfavoriteAuthor() {
        // given
        Favorite favorite = Favorite.builder().userId(1).favoritedId(2).favoriteType(ARTICLE).build();
        FavoriteId favoriteId = FavoriteId.builder().userId(1L).favoritedId(2L).favoriteType(ARTICLE).build();
        favoriteRepository.save(favorite);

        em.flush();
        em.clear();

        Optional<Favorite> optionalFavorite1 = favoriteRepository.findById(favoriteId);
        assertThat(optionalFavorite1).isNotEmpty();

        // when
        favoriteServiceFactory.getService(ARTICLE).unfavoriteAuthor(1, 2);


        Optional<Favorite> optionalFavorite2 = favoriteRepository.findById(favoriteId);

        // then
        assertThat(optionalFavorite2).isEmpty();
    }
}
