package io.realworld.user.app;

import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.FollowRelationId;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultFollowRelationService implements FollowRelationService{

    final private FollowRelationRepository followRelationRepository;

    @Override
    public boolean isFollowing(long followeeId, long followerId) {
        Optional<FollowRelation> followRelation = followRelationRepository.findById(new FollowRelationId(followerId, followeeId));
        return followRelation.isPresent();
    }
}
