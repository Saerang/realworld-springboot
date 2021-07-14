package io.realworld.user.app;

import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.app.exception.UserNotFoundException;
import io.realworld.user.domain.FollowRelation;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.FollowRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultProfileService implements ProfileService {

    private final FollowRelationRepository followRelationRepository;
    private final UserService userService;

    @Override
    public ProfileResponseDto getProfile(String username) {
        User user = userService.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return Mappers.toProfileResponseDto(user, false);
    }

    @Override
    public ProfileResponseDto followUser(long followerUserId, String username) {
        // TODO: controller 에서 id를 넘길까, userService 혹은 현제 세션에 있는 유저로 가져올까.
        // User user = userService.getCurrentUser();
        User followeeUser = userService.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.save(followRelation);

        return Mappers.toProfileResponseDto(followeeUser, true);
    }

    @Override
    public ProfileResponseDto unfollowUser(long followerUserId, String username) {
        // TODO: controller 에서 id를 넘길까, userService 혹은 현제 세션에 있는 유저로 가져올까.
        // User user = userService.getCurrentUser();
        User followeeUser = userService.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        FollowRelation followRelation = new FollowRelation(followerUserId, followeeUser.getId());
        followRelationRepository.delete(followRelation);

        return Mappers.toProfileResponseDto(followeeUser, false);
    }
}
