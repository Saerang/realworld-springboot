package io.realworld.favorite.domain.repository;

import io.realworld.common.exception.FavoriteNotFoundException;
import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.favorite.domain.Favorite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FavoriteRepositoryTest {

    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void persistence() {
        // given
        Favorite favorite = Favorite.builder().userId(1L).favoritedId(1L).favoriteType(FavoriteType.ARTICLE).build();

        // when
        Favorite savedFavorite = favoriteRepository.save(favorite);
        em.flush();
        em.clear();

        // then
        Favorite findFavorite = favoriteRepository.findById(savedFavorite.getFavoriteId())
                .orElseThrow(() -> new FavoriteNotFoundException(favorite.getFavoriteId().getUserId(), favorite.getFavoriteId().getUserId()));

        assertThat(savedFavorite.getFavoriteId().getUserId()).isEqualTo(findFavorite.getFavoriteId().getUserId());
        assertThat(savedFavorite.getFavoriteId().getFavoritedId()).isEqualTo(findFavorite.getFavoriteId().getFavoritedId());
    }

}
