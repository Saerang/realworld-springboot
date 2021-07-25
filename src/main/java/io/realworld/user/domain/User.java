package io.realworld.user.domain;

import io.realworld.common.base.BaseTimeEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String username;

    private String bio;

    private String image;

    public User(String email, String password, String username) {
        this(email, password, username, null, null);
    }

    @Builder
    public User(String email, String password, String username, String bio, String image) {
        Assert.state(StringUtils.isNotBlank(email), "email may not be blank.");
        Assert.state(StringUtils.isNotBlank(password), "password may not be blank.");
        Assert.state(StringUtils.isNotBlank(username), "username may not be blank.");

        this.email = email;
        this.password = password;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public void updateUserInfo(String email, String username, String password, PasswordEncoder passwordEncoder, String image, String bio) {
        this.email = email;
        if (StringUtils.isNotBlank(password)) {
            this.encodePassword(passwordEncoder);
        }
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

}
