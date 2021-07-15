package io.realworld.common.config;

import io.realworld.common.security.JwtAccessDeniedHandler;
import io.realworld.common.security.JwtAuthenticationEntryPoint;
import io.realworld.common.security.JwtSecurityConfig;
import io.realworld.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) {
        web.
                ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/favicon.io"
                );
    }

    /*
    User
        Auth:
        GET /api/user : current user
        PUT /api/user : update

        No Auth:
        POST /api/users/login : login
        POST /api/users : registration

    PROFILE
        Auth:
        POST: /api/profiles/:username/follow : follow
        DELETE: /api/profiles/:username/follow : unfollow

        NoAuth:
        GET /api/profiles/:username : get profile

    Article
    Auth:
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                // exception
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // h2 console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                // session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // url
                .and()
                .authorizeRequests()
                .antMatchers(POST, "/api/users", "/api/users/login").permitAll()
                .antMatchers(POST, "/test/notfound").permitAll()
                .antMatchers(GET, "/api/profiles/**", "/api/articles/**", "/api/tags").permitAll()
//                .antMatchers(GET, "/api/articles/**", "/api/tags").permitAll()
                .anyRequest().authenticated()
                // security configurer
                .and()
                .apply(new JwtSecurityConfig(jwtTokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
