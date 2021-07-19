package io.realworld.user.domain.repository;

import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRelationRepository extends JpaRepository<FollowRelation, FollowRelationId> {

    List<FollowRelation> findByFollowRelationId_FollowerId(long followerId);

}
