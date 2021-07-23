package io.realworld.user.app;

import io.realworld.common.WithDefaultUser;
import io.realworld.common.exception.UserAlreadyExistException;
import io.realworld.common.exception.UserNotFoundException;
import io.realworld.user.api.dto.UserCreateRequestDto;
import io.realworld.user.api.dto.UserLoginRequestDto;
import io.realworld.user.api.dto.UserUpdateRequestDto;
import io.realworld.user.domain.User;
import io.realworld.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static io.realworld.user.app.enumerate.LoginType.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        //given
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username("createUser")
                .email("createUser@email.com")
                .password("12345678")
                .build();

        //when
        User user = userService.createUser(dto);

        em.flush();
        em.clear();

        User findUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(EMAIL.getMessage() + user.getEmail()));

        //then
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    void createUser_dup() {
        //given
        UserCreateRequestDto dto = UserCreateRequestDto.builder()
                .username("realworld101")
                .email("realworld101@email.com")
                .password("1234")
                .build();

        //when
        assertThatThrownBy(() -> userService.createUser(dto)).isInstanceOf(UserAlreadyExistException.class);
    }

    @Test
    @WithDefaultUser
    void updateUser() {
        //given
        String email = "update@email.com";
        String username = "updateUsername";
        String password = "87654321";
        String bio = "updateBio";
        String image = "updateImage";

        UserUpdateRequestDto dto = UserUpdateRequestDto.builder()
                .email(email)
                .username(username)
                .password(password)
                .bio(bio)
                .image(image)
                .build();

        //when
        User user = userService.updateUser(dto);
        User findUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(EMAIL.getMessage() + user.getEmail()));

        //then
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getBio()).isEqualTo(bio);
        assertThat(findUser.getImage()).isEqualTo(image);
    }

    @Test
    void getUserByUsername() {
        //given
        User user = getDefaultUser();

        //when
        User findUser = userService.getUserByUsername(user.getUsername());

        //then
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
        assertThat(user.getUsername()).isEqualTo(findUser.getUsername());
    }
    
    @Test
    void getCurrentUser_authNotFound() {
        // given 
        // when
        // then
        assertThatThrownBy(() -> userService.getCurrentUser()).isInstanceOf(UserNotFoundException.class).hasMessage("User dose not found.");
    }

    @Test
    void login() {
        //given
        UserLoginRequestDto dto = UserLoginRequestDto.builder()
                .email("realworld101@email.com")
                .password("12345678")
                .build();

        //when
        User result = userService.login(dto);

        //then
        assertThat(result.getEmail()).isEqualTo("realworld101@email.com");
    }

    @Test
    void getUsers_byIds() {
        // given
        List<Long> userIds = List.of(101L, 102L, 103L);

        // when
        List<User> users = userService.getUsersByIds(userIds);

        // then
        assertThat(users).hasSize(3);
        assertThat(users).extracting("id").isEqualTo(userIds);
    }

    private User getDefaultUser() {
        return User.builder()
                .username("realworld101")
                .email("realworld101@email.com")
                .password("12345678")
                .build();
    }

}

