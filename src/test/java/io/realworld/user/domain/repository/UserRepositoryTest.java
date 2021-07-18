package io.realworld.user.domain.repository;

import io.realworld.common.exception.UserNotFoundException;
import io.realworld.user.domain.Profile;
import io.realworld.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static io.realworld.user.app.enumerate.LoginType.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void persistence() {
        //given
        Profile profile = Profile.builder()
                .username("new realworld")
                .bio("I work at statefarm")
                .build();
        User user = User.builder()
                .profile(profile)
                .email("new_realworld1@email.com")
                .build();
        assertThat(user.getId()).isNull();

        //when
        userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);

        em.flush();
        em.clear();

        User findUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(USER_ID.getMessage() + user.getId()));


        //then
        assertThat(user.getId()).isEqualTo(findUser.getId());
        assertThat(user.getProfile().getUsername()).isEqualTo(findUser.getProfile().getUsername());
    }
}
