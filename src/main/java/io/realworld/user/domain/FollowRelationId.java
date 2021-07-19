package io.realworld.user.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowRelationId implements Serializable {
    private Long followerId;

    private Long followeeId;

    public FollowRelationId(Long followerId, Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }
}
