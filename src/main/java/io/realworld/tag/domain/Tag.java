package io.realworld.tag.domain;

import io.realworld.common.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Long id;

    @Column(unique = true)
    private String tag;

    public Tag(String tag) {
        this(null, tag);
    }

    @Builder
    public Tag(Long id, String tag) {
        Assert.state(StringUtils.isNotBlank(tag), "tag may not be blank.");

        this.id = id;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag1 = (Tag) o;
        return Objects.equals(getTag(), tag1.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTag());
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }
}
