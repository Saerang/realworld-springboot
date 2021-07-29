package io.realworld.comment.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@ToString
@NoArgsConstructor
public class CommentResponseDto {

    @JsonProperty("comment")
    private CommentDto commentDto;

    @Builder
    public CommentResponseDto(CommentDto commentDto) {
        this.commentDto = commentDto;
    }

    @Getter
    @NoArgsConstructor
    public static class CommentDto {
        private Long id;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private String body;
        @JsonProperty("author")
        private UserDto userDto;

        @Builder
        public CommentDto(Long id, String body, ZonedDateTime createdAt, ZonedDateTime updatedAt, UserDto userDto) {
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
