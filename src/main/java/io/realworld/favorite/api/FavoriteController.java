package io.realworld.favorite.api;

import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.favorite.app.FavoriteMapper;
import io.realworld.favorite.app.enumerate.FavoriteType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FavoriteController {

    private final FavoriteMapper favoriteMapper;

    public FavoriteController(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    @PostMapping("/api/articles/{slug}/favorite")
    public SingleArticleResponseDto articleFavorite(@PathVariable String slug) {
        return null;
    }
}
