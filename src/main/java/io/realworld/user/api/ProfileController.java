package io.realworld.user.api;

import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.app.AuthenticationService;
import io.realworld.user.app.ProfileService;
import io.realworld.user.app.dto.Mappers;
import io.realworld.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final AuthenticationService authenticationService;

    @GetMapping("/profiles/{username}")
    public ProfileResponseDto getProfile(@PathVariable String username) {
        Pair<User, Boolean> user = profileService.getProfile(username);
        return getProfileResponseDto(user);
    }

    @PostMapping("/profiles/{username}/follow")
    public ProfileResponseDto followUser(@PathVariable String username) {
        Pair<User, Boolean> user = profileService.followUser(getCurrentUserId(), username);
        return getProfileResponseDto(user);
    }

    @DeleteMapping("/profiles/{username}/follow")
    public ProfileResponseDto unfollowUser(@PathVariable String username) {
        Pair<User, Boolean> user = profileService.unfollowUser(getCurrentUserId(), username);
        return getProfileResponseDto(user);
    }

    private Long getCurrentUserId() {
        return authenticationService.getCurrentUserId();
    }

    private ProfileResponseDto getProfileResponseDto(Pair<User, Boolean> user) {
        return Mappers.toProfileResponseDto(user.getKey(), user.getValue());
    }
}

