package io.realworld.comment.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CommentCreateDto {
    @JsonProperty("comment")
    private CommentDto commentDto;

    @Builder
    public CommentCreateDto(CommentDto commentDto) {
        this.commentDto = commentDto;
    }

    @Getter
    @NoArgsConstructor
    public static class CommentDto {
        private String body;

        @Builder
        public CommentDto(String body) {
            this.body = body;
        }
    }
}
