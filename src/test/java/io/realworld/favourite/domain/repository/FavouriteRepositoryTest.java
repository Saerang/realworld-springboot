package io.realworld.favourite.domain.repository;

import io.realworld.common.exception.FavouriteNotFound;
import io.realworld.favourite.app.enumerate.FavouriteType;
import io.realworld.favourite.domain.Favourite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FavouriteRepositoryTest {

    @Autowired
    FavouriteRepository favouriteRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void persistence() {
        // given
        Favourite favourite = Favourite.builder().userId(1).authorId(1).favouriteType(FavouriteType.ARTICLE).build();

        // when
        Favourite savedFavourite = favouriteRepository.save(favourite);
        em.flush();
        em.clear();

        // then
        Favourite findFavourite = favouriteRepository.findById(savedFavourite.getFavouriteId())
                .orElseThrow(() -> new FavouriteNotFound(favourite.getFavouriteId().getUserId(), favourite.getFavouriteId().getAuthorId()));

        assertThat(savedFavourite.getFavouriteId().getUserId()).isEqualTo(findFavourite.getFavouriteId().getUserId());
        assertThat(savedFavourite.getFavouriteId().getAuthorId()).isEqualTo(findFavourite.getFavouriteId().getAuthorId());
        assertThat(savedFavourite.getFavouriteId().getFavouriteType()).isEqualTo(findFavourite.getFavouriteId().getFavouriteType());
    }

}
