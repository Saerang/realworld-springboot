package io.realworld.favorite.domain;

import io.realworld.favorite.app.enumerate.FavoriteType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FavoriteTest {

    @Test
    void createFavorite() {
        // given
        // when
        Favorite favorite = Favorite.builder().userId(1).favoritedId(1).favoriteType(FavoriteType.ARTICLE).build();

        // then
        assertThat(favorite.getFavoriteId().getUserId()).isEqualTo(1);
        assertThat(favorite.getFavoriteId().getFavoritedId()).isEqualTo(1);
    }
}
