package io.realworld.user.app;

import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DefaultFollowRelationService implements FollowRelationService{

    final private FollowRelationRepository followRelationRepository;

    public DefaultFollowRelationService(FollowRelationRepository followRelationRepository) {
        this.followRelationRepository = followRelationRepository;
    }

    @Override
    public boolean isFollowing(Long followeeId, Long followerId) {
        if(followeeId == null || followerId == null) {
            return false;
        }

        FollowRelationId followRelationId = FollowRelationId.builder().followerId(followerId).followeeId(followeeId).build();
        Optional<FollowRelation> optionalFollowRelation = followRelationRepository.findById(followRelationId);
        return optionalFollowRelation.isPresent();
    }

    @Override
    public List<FollowRelation> getFollowRelations(Long followeeId) {
        if(followeeId == null) {
            return Collections.emptyList();
        }

        return followRelationRepository.findByFolloweeId(followeeId);
    }
}
