package io.realworld.user.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
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
}
