package io.realworld.user.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode(of = {"followerId", "followeeId"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowRelationId implements Serializable {
    private Long followerId;

    private Long followeeId;

    @Builder
    public FollowRelationId(Long followerId, Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }
}
