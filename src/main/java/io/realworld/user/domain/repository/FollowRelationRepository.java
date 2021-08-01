package io.realworld.user.domain.repository;

import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRelationRepository extends JpaRepository<FollowRelation, FollowRelationId> {

    @Query("select f from FollowRelation f where f.followRelationId.followeeId = :followeeId")
    List<FollowRelation> findByFolloweeId(@Param("followeeId") long followeeId);
}
