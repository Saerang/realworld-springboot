package io.realworld.comment.api;

import io.realworld.comment.api.dto.CommentCreateDto;
import io.realworld.comment.api.dto.CommentResponseDto;
import io.realworld.comment.api.dto.CommentsResponseDto;
import io.realworld.comment.app.CommentService;
import io.realworld.comment.domain.Comment;
import io.realworld.common.mapper.Mappers;
import io.realworld.favorite.app.enumerate.FavoriteType;
import io.realworld.user.app.AuthenticationService;
import io.realworld.user.app.FollowRelationService;
import io.realworld.user.app.UserService;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.data.util.Pair.toMap;

@RestController
@RequestMapping("/api")
public class CommentController {

    final private CommentService commentService;
    final private AuthenticationService authenticationService;
    // TODO: Service 에서 하는게 맞는지? Controller 에서 하는게 맞는지? refactoring 생각해보기
    final private UserService userService;
    final private FollowRelationService followRelationService;

    public CommentController(CommentService commentService, AuthenticationService authenticationService, UserService userService, FollowRelationService followRelationService) {
        this.commentService = commentService;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.followRelationService = followRelationService;
    }

    @PostMapping("/articles/{slug}/comments")
    public CommentResponseDto createComment(
            @RequestBody CommentCreateDto dto,
            @PathVariable String slug
    ) {
        Comment comment = commentService.createComment(dto.getCommentDto().getBody(), slug, authenticationService.getCurrentUserId());
        return toCommentResponseDto(comment);
    }

    @GetMapping("/articles/{slug}/comments")
    public CommentsResponseDto getComments(@PathVariable String slug) {
        List<Comment> comments = commentService.getComments(slug);

        List<Long> userIds = comments.stream().map(Comment::getUserId).collect(Collectors.toList());
        Map<Long, User> userMap = userService.getUsersByIds(userIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));

        List<Long> followerIds = followRelationService.getFollowRelations(authenticationService.getCurrentUserId()).stream()
                .map(FollowRelation::getFollowRelationId)
                .map(FollowRelationId::getFollowerId)
                .collect(Collectors.toList());

        return toCommentsResponseDto(comments, userMap, followerIds);
    }

    private CommentResponseDto toCommentResponseDto(Comment comment) {
        return Mappers.toCommentResponseDto(comment, authenticationService.getCurrentUser(), false);
    }

    private CommentsResponseDto toCommentsResponseDto(List<Comment> comments, Map<Long, User> userMap, List<Long> followerIds) {
        return Mappers.toCommentsResponseDto(comments, userMap, followerIds);
    }
}
