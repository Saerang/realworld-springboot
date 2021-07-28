package io.realworld.comment.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class CommentsResponseDto {

    @JsonProperty("comments")
    private List<CommentDto> commentsDto;

    @Builder
    public CommentsResponseDto(List<CommentDto> commentsDto) {
        this.commentsDto = commentsDto;
    }

    @Getter
    @NoArgsConstructor
    public static class CommentDto {
        private Long id;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String body;
        @JsonProperty("author")
        private UserDto userDto;

        @Builder
        public CommentDto(Long id, String body, LocalDateTime createdAt, LocalDateTime updatedAt, UserDto userDto) {
            this.id = id;
            this.body = body;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.userDto = userDto;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UserDto {
        private String username;
        private String bio;
        private String image;
        private boolean following;

        @Builder
        public UserDto(String username, String bio, String image, boolean following) {
            this.username = username;
            this.bio = bio;
            this.image = image;
            this.following = following;
        }
    }
}
