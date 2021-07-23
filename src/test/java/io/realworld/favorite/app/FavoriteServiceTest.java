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
import java.util.List;
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

    // TODO: beforeEach 로 데이터 세팅 해두는게 좋아보임.

    @Test
    void getFavorited_false() {
        // given
        // when
        boolean favorited = favoriteServiceFactory.getService(ARTICLE).isFavorited(103L, 101L);

        // then
        assertThat(favorited).isFalse();
    }

    @Test
    void getFavorited_true() {
        // given
        Favorite favorite = Favorite.builder().userId(102L).favoritedId(103L).favoriteType(ARTICLE).build();
        favoriteRepository.save(favorite);

        // when
        boolean favorited = favoriteServiceFactory.getService(ARTICLE).isFavorited(102L, 103L);

        // then
        assertThat(favorited).isTrue();
    }

    @Test
    void getFavorites_byFavoritedId() {
        // given
        Favorite favorite1 = Favorite.builder().userId(101L).favoritedId(102L).favoriteType(ARTICLE).build();
        Favorite favorite2 = Favorite.builder().userId(102L).favoritedId(102L).favoriteType(ARTICLE).build();
        favoriteRepository.saveAll(List.of(favorite1, favorite2));
        em.flush();
        em.clear();

        // when
        List<Favorite> favorites = favoriteServiceFactory.getService(ARTICLE).getFavorites(102);

        // then
        assertThat(favorites).hasSize(2);
        assertThat(favorites).extracting("favoriteId.userId").contains(101L, 102L);
    }

    @Test
    void getFavoritedIds_byUserId() {
        // given
        long userId = 101L;
        // when
        List<Long> favoritedIds = favoriteServiceFactory.getService(ARTICLE).getFavoritedIds(userId);

        // then
        assertThat(favoritedIds).hasSize(4);
        assertThat(favoritedIds).contains(101L, 102L, 103L, 104L);
    }

    @Test
    void favoriteAuthor() {
        // given
        FavoriteId favoriteId = FavoriteId.builder().userId(102L).favoritedId(101L).favoriteType(ARTICLE).build();

        // when
        favoriteServiceFactory.getService(ARTICLE).favoriteAuthor(102L, 101L);
        em.flush();
        em.clear();

        Favorite favorite = favoriteRepository.findById(favoriteId).orElseThrow(FavoriteNotFoundException::new);

        // then
        assertThat(favorite.getFavoriteId().getUserId()).isEqualTo(102L);
        assertThat(favorite.getFavoriteId().getFavoritedId()).isEqualTo(101L);
    }

    @Test
    void unfavoriteAuthor() {
        // given
        Favorite favorite = Favorite.builder().userId(101L).favoritedId(102L).favoriteType(ARTICLE).build();
        favoriteRepository.save(favorite);

        em.flush();
        em.clear();

        FavoriteId favoriteId = FavoriteId.builder().userId(101L).favoritedId(102L).favoriteType(ARTICLE).build();
        Optional<Favorite> optionalFavorite1 = favoriteRepository.findById(favoriteId);
        assertThat(optionalFavorite1).isNotEmpty();

        // when
        favoriteServiceFactory.getService(ARTICLE).unfavoriteAuthor(101L, 102L);


        Optional<Favorite> optionalFavorite2 = favoriteRepository.findById(favoriteId);

        // then
        assertThat(optionalFavorite2).isEmpty();
    }
}
