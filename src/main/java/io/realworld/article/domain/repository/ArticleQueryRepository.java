package io.realworld.article.domain.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.realworld.article.app.dto.ArticleSearchCondition;
import io.realworld.article.domain.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static io.realworld.article.domain.QArticle.article;
import static io.realworld.article.domain.QArticleTag.articleTag;
import static io.realworld.favorite.domain.QFavorite.favorite;
import static io.realworld.tag.domain.QTag.tag1;
import static io.realworld.user.domain.QUser.user;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Repository
public class ArticleQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ArticleQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<Article> search(ArticleSearchCondition condition, Pageable pageable) {
        if (StringUtils.isNotBlank(condition.getFavorited())) {
            return searchFromFavorited(condition.getFavorited(), pageable);
        } else if (StringUtils.isNotBlank(condition.getTag())) {
            return searchFromTag(condition.getTag(), pageable);
        } else if (StringUtils.isNotBlank(condition.getAuthor())) {
            return searchFromAuthor(condition.getAuthor(), pageable);
        } else {
            return search(pageable);
        }

    }

    public Page<Article> search(Pageable pageable) {
        JPAQuery<Article> query = queryFactory
                .selectFrom(article)
                .from(article)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        orderBy(pageable, query);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }



    private Page<Article> searchFromTag(String tag, Pageable pageable) {
        JPAQuery<Article> query = queryFactory
                .selectFrom(article)
                .distinct()
                .join(articleTag).on(articleTag.article.id.eq(article.id))
                .join(tag1).on(articleTag.tagId.eq(tag1.id))
                .where(tagEq(tag))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        orderBy(pageable, query);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }

    private Page<Article> searchFromAuthor(String auth, Pageable pageable) {
        JPAQuery<Article> query = queryFactory
                .selectFrom(article)
                .join(user).on(article.userId.eq(user.id))
                .where(authorEq(auth))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        orderBy(pageable, query);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }

    private Page<Article> searchFromFavorited(String favorited, Pageable pageable) {
        JPAQuery<Article> query = queryFactory
                .selectFrom(article)
                .join(favorite).on(article.id.eq(favorite.favoriteId.favoritedId))
                .join(user).on(favorite.favoriteId.userId.eq(user.id))
                .where(favoritedEq(favorited))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());


        orderBy(pageable, query);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }

    private void orderBy(Pageable pageable, JPAQuery<Article> query) {
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(article.getType(), article.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }
    }

    private BooleanExpression tagEq(String tag) {
        return isEmpty(tag) ? null : tag1.tag.eq(tag);
    }

    private BooleanExpression authorEq(String author) {
        return isEmpty(author) ? null : user.username.eq(author);
    }

    private BooleanExpression favoritedEq(String favorited) {
        return isEmpty(favorited) ? null : user.username.eq(favorited);
    }
}
