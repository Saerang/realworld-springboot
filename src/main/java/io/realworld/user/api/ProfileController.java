package io.realworld.user.api;

import io.realworld.user.api.dto.ProfileResponseDto;
import io.realworld.user.app.ProfileService;
import io.realworld.user.app.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping("/profiles/{username}")
    public ProfileResponseDto getProfile(@PathVariable String username) {
        return profileService.getProfile(username);
    }

    @PostMapping("/profiles/{username}/follow")
    public ProfileResponseDto followUser(@PathVariable String username) {
        return profileService.followUser(getUserId(), username);
    }

    @DeleteMapping("/profiles/{username}/follow")
    public ProfileResponseDto unfollowUser(@PathVariable String username) {
        return profileService.unfollowUser(getUserId(), username);
    }

    private long getUserId() {
        return userService.findCurrentUser().getId();
    }
}

