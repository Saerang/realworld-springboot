package io.realworld.favourite.domain;

import org.junit.jupiter.api.Test;

import static io.realworld.favourite.app.enumerate.FavouriteType.ARTICLE;
import static org.assertj.core.api.Assertions.assertThat;

public class FavouriteTest {

    @Test
    void createFavourite() {
        // given
        // when
        Favourite favourite = Favourite.builder().userId(1).authorId(1).favouriteType(ARTICLE).build();

        // then
        assertThat(favourite.getFavouriteId().getUserId()).isEqualTo(1);
        assertThat(favourite.getFavouriteId().getAuthorId()).isEqualTo(1);
        assertThat(favourite.getFavouriteId().getFavouriteType()).isEqualTo(ARTICLE);
    }
}
