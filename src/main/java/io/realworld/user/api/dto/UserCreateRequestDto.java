package io.realworld.user.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserCreateRequestDto {
    @JsonProperty("user")
    private UserDto userDto;

    @Builder
    public UserCreateRequestDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Getter @ToString
    @NoArgsConstructor
    public static class UserDto {
        private String username;
        private String email;
        private String password;

        @Builder
        public UserDto(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
}
