package io.realworld.user.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;

    @Embedded
    private Profile profile;

    public User(String email, String password, String username) {
        this(email, password, Profile.builder().username(username).build());
    }

    @Builder
    public User(String email, String password, Profile profile) {
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public void updateUserInfo(String email, String username, String password, String image, String bio) {
        this.email = email;
        this.password = password;
        this.profile.updateProfile(username, bio, image);
    }

    public boolean isPasswordMatched(String password) {
        return StringUtils.equals(password, this.password);
    }
}
