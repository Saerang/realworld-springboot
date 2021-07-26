package io.realworld.article.api;

import io.realworld.article.api.dto.ArticleCreateDto;
import io.realworld.article.api.dto.MultipleArticlesResponseDto;
import io.realworld.article.api.dto.SingleArticleResponseDto;
import io.realworld.article.app.ArticleMapperService;
import io.realworld.user.app.AuthenticationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ArticleController {

    final private ArticleMapperService articleMapperService;
    final private AuthenticationService authenticationService;

    public ArticleController(ArticleMapperService articleMapperService, AuthenticationService authenticationService) {
        this.articleMapperService = articleMapperService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/articles")
    public MultipleArticlesResponseDto getArticles(
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String favorited,
            @RequestParam(required = false, defaultValue = "0") final int offset,
            @RequestParam(required = false, defaultValue = "20") final int limit,
            @RequestParam(required = false, defaultValue = "DESC") final Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "createdAt") final String sortProperty
    ) {
        PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortDirection, sortProperty));
        return articleMapperService.getArticles(tag, author, favorited, pageable, getCurrentUserId());
    }

    @GetMapping("/articles/feed")
    public MultipleArticlesResponseDto getFeedArticles(
            @RequestParam(required = false, defaultValue = "0") final int offset,
            @RequestParam(required = false, defaultValue = "20") final int limit,
            @RequestParam(required = false, defaultValue = "DESC") final Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "createdAt") final String sortProperty
    ) {
        PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortDirection, sortProperty));
        return articleMapperService.getFeedArticles(pageable, getCurrentUserId());
    }

    @GetMapping("/articles/{slug}")
    public SingleArticleResponseDto getArticle(@PathVariable String slug) {
        return articleMapperService.getSingleArticleResponseDto(slug, getCurrentUserId());
    }

    @PostMapping("/articles")
    public SingleArticleResponseDto createArticle(@RequestBody ArticleCreateDto articleCreateDto) {
        return articleMapperService.createArticle(articleCreateDto, getCurrentUserId());
    }

    private Long getCurrentUserId() {
        return authenticationService.getCurrentUserId();
    }
}
