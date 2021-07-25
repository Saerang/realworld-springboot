package io.realworld.user.domain;

import io.realworld.common.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowRelation extends BaseTimeEntity {
    @EmbeddedId
    private FollowRelationId followRelationId;

    public FollowRelation(FollowRelationId followRelationId) {
        this.followRelationId = followRelationId;
    }

    public FollowRelation(long followerId, long followeeId) {
        this.followRelationId = new FollowRelationId(followerId, followeeId);
    }
}
