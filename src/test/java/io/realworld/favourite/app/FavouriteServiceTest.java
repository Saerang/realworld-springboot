package io.realworld.favourite.app;

import io.realworld.common.exception.FavouriteNotFoundException;
import io.realworld.favourite.app.enumerate.FavouriteType;
import io.realworld.favourite.domain.Favourite;
import io.realworld.favourite.domain.FavouriteId;
import io.realworld.favourite.domain.repository.FavouriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static io.realworld.favourite.app.enumerate.FavouriteType.*;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FavouriteServiceTest {

    @Autowired
    FavouriteServiceFactory favouriteServiceFactory;
    @Autowired
    FavouriteRepository favouriteRepository;
    @Autowired
    EntityManager em;

    @Test
    void getFavourited_false() {
        // given

        // when
        boolean favourited = favouriteServiceFactory.getService(ARTICLE).isFavourited(1, 2);

        // then
        assertThat(favourited).isFalse();
    }

    @Test
    void getFavourited_true() {
        // given
        Favourite favourite = Favourite.builder().userId(1).authorId(2).favouriteType(ARTICLE).build();
        favouriteRepository.save(favourite);

        // when
        boolean favourited = favouriteServiceFactory.getService(ARTICLE).isFavourited(1, 2);

        // then
        assertThat(favourited).isTrue();
    }

    @Test
    void favouriteAuthor() {
        // given

        // when
        favouriteServiceFactory.getService(ARTICLE).favouriteAuthor(1, 2);

        FavouriteId favouriteId = FavouriteId.builder().userId(1L).authorId(2L).favouriteType(ARTICLE).build();
        Favourite favourite = favouriteRepository.findById(favouriteId).orElseThrow(FavouriteNotFoundException::new);

        // then
        assertThat(favourite.getFavouriteId().getUserId()).isEqualTo(1);
        assertThat(favourite.getFavouriteId().getAuthorId()).isEqualTo(2);
    }

    @Test
    void unfavoriteAuthor() {
        // given
        Favourite favourite = Favourite.builder().userId(1).authorId(2).favouriteType(ARTICLE).build();
        FavouriteId favouriteId = FavouriteId.builder().userId(1L).authorId(2L).favouriteType(ARTICLE).build();
        favouriteRepository.save(favourite);
        em.flush();
        em.clear();

        Optional<Favourite> optionalFavourite1 = favouriteRepository.findById(favouriteId);
        assertThat(optionalFavourite1).isNotEmpty();


        // when
        favouriteServiceFactory.getService(ARTICLE).unfavoriteAuthor(1, 2);


        Optional<Favourite> optionalFavourite2 = favouriteRepository.findById(favouriteId);

        // then
        assertThat(optionalFavourite2).isEmpty();
    }
}
