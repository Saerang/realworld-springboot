package io.realworld.article.api.dto;

import io.realworld.tag.app.dto.TagRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class ArticleUpdateDto {
    private  String title;
    private String description;
    private String body;
    private Set<TagRequestDto> tags;


    @Builder
    public ArticleUpdateDto(String title, String description, String body, Set<TagRequestDto> tags) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
    }
}
